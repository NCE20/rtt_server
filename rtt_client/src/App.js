import React, { useState, useEffect, useRef } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import * as THREE from "three";
import "./App.css";
import "./style.css";

const App = () => {
  const [nodeContent, setnodeContent] = useState("");
  const [linkContent, setlinkContent] = useState("");

  const canvasRef = useRef(null);
  const cameraRef = useRef(null);
  const isLeftMouseDownRef = useRef(false);
  const isRightMouseDownRef = useRef(false);
  const previousMousePositionRef = useRef({ x: 0, y: 0 });

  ////

  ////

  useEffect(() => {
    // 파일을 불러오는 비동기 함수
    const readFile = async () => {
      try {
        const noderesponse = await fetch("/data/node.txt"); // 첫 번째 파일 경로
        const nodetext = await noderesponse.text();

        const nodelines = nodetext.split("#");
        const filterednode = nodelines.join("\n");

        setnodeContent(filterednode);

        const linkresponse = await fetch("/data/link.txt"); // 두 번째 파일 경로
        const linktext = await linkresponse.text();

        const linklines = linktext.split("#");
        const filteredlink = linklines.join("\n");

        setlinkContent(filteredlink);
      } catch (error) {
        console.error("Error reading file:", error);
      }
    };

    readFile();

    //

    let scene, renderer;
    let cubes = []; // 큐브들을 담을 배열

    const init = () => {
      // 씬 생성
      scene = new THREE.Scene();

      // 카메라 생성 및 초기 위치 설정
      const camera = new THREE.PerspectiveCamera(
        75,
        window.innerWidth / window.innerHeight,
        0.1,
        1000
      );
      camera.position.z = 5;
      cameraRef.current = camera;

      // WebGL 렌더러 생성
      renderer = new THREE.WebGLRenderer({ canvas: canvasRef.current });
      //renderer.setSize(window.innerWidth, window.innerHeight);
      renderer.setSize(1000, 750);

      // 큐브들 생성
      for (let i = 0; i < 100; i++) {
        const geometry = new THREE.BoxGeometry(0.2, 0.2, 0.2);
        const material = new THREE.MeshBasicMaterial({ color: 0xffffff });
        const cube = new THREE.Mesh(geometry, material);

        // 랜덤한 위치 설정
        cube.position.x = Math.random() * 10 - 5;
        cube.position.y = Math.random() * 10 - 5;
        cube.position.z = Math.random() * 10 - 5;

        cubes.push(cube);
        scene.add(cube);

        // 윤곽선 생성
        const edges = new THREE.EdgesGeometry(geometry);
        const lineMaterial = new THREE.LineBasicMaterial({ color: 0x000000 });
        const wireframe = new THREE.LineSegments(edges, lineMaterial);
        cube.add(wireframe);
      }

      // 이벤트 리스너 등록
      window.addEventListener("resize", handleWindowResize);
      canvasRef.current?.addEventListener("mousedown", handleMouseDown);
      canvasRef.current?.addEventListener("mouseup", handleMouseUp);
      canvasRef.current?.addEventListener("mousemove", handleMouseMove);
      canvasRef.current?.addEventListener("wheel", handleMouseWheel);
      canvasRef.current?.addEventListener("contextmenu", handleContextMenu); // 우클릭 이벤트 처리
    };

    const handleWindowResize = () => {
      const width = window.innerWidth;
      const height = window.innerHeight;

      cameraRef.current.aspect = width / height;
      cameraRef.current.updateProjectionMatrix();

      renderer.setSize(width, height);
    };

    const handleMouseDown = (event) => {
      if (event.button === 0) {
        isLeftMouseDownRef.current = true;
      } else if (event.button === 2) {
        isRightMouseDownRef.current = true;
      }
    };

    const handleMouseUp = () => {
      isLeftMouseDownRef.current = false;
      isRightMouseDownRef.current = false;
      previousMousePositionRef.current = { x: 0, y: 0 };
    };

    const handleMouseMove = (event) => {
      const mouseX = event.clientX;
      const mouseY = event.clientY;

      if (isLeftMouseDownRef.current) {
        const movementSpeed = 0.01;
        const camera = cameraRef.current;

        if (
          previousMousePositionRef.current.x === 0 &&
          previousMousePositionRef.current.y === 0
        ) {
          previousMousePositionRef.current = { x: mouseX, y: mouseY };
          return;
        }

        const deltaX = mouseX - previousMousePositionRef.current.x;
        const deltaY = mouseY - previousMousePositionRef.current.y;

        // 현재 카메라의 회전 각도를 적용한 이동 벡터 계산
        const rotationMatrix = new THREE.Matrix4().makeRotationFromQuaternion(
          camera.quaternion
        );
        const movementVector = new THREE.Vector3(
          deltaX,
          -deltaY,
          0
        ).applyMatrix4(rotationMatrix);

        camera.position.add(movementVector.multiplyScalar(movementSpeed));

        previousMousePositionRef.current = { x: mouseX, y: mouseY };
      } else if (isRightMouseDownRef.current) {
        // 마우스 우클릭 상태에서의 동작 구현
        const rotationSpeed = 0.01;
        const camera = cameraRef.current;

        if (
          previousMousePositionRef.current.x === 0 &&
          previousMousePositionRef.current.y === 0
        ) {
          previousMousePositionRef.current = { x: mouseX, y: mouseY };
          return;
        }

        const deltaX = mouseX - previousMousePositionRef.current.x;
        const deltaY = mouseY - previousMousePositionRef.current.y;

        camera.rotation.y += deltaX * rotationSpeed;
        camera.rotation.x += deltaY * rotationSpeed;

        previousMousePositionRef.current = { x: mouseX, y: mouseY };
      }
    };

    const handleMouseWheel = (event) => {
      const delta = Math.max(-10, Math.min(10, event.deltaY));
      const zoomSpeed = 0.1;

      const camera = cameraRef.current;

      // 마우스 휠 방향에 따라 카메라의 위치 조정하여 확대 또는 축소
      const rotationMatrix = new THREE.Matrix4().makeRotationFromQuaternion(
        camera.quaternion
      );
      const zoomVector = new THREE.Vector3(
        0,
        0,
        delta * zoomSpeed
      ).applyMatrix4(rotationMatrix);

      camera.position.add(zoomVector);

      // 카메라의 z 좌표 제한 설정 (확대/축소 범위 제한)
      camera.position.z = Math.max(-10, Math.min(100, camera.position.z));
    };

    const handleContextMenu = (event) => {
      event.preventDefault(); // 기본 동작(팝업 메뉴 표시) 막기
    };

    const animate = () => {
      requestAnimationFrame(animate);
      renderer.render(scene, cameraRef.current);
    };

    init();
    animate();

    return () => {
      window.removeEventListener("resize", handleWindowResize);
      canvasRef.current?.removeEventListener("mousedown", handleMouseDown);
      canvasRef.current?.removeEventListener("mouseup", handleMouseUp);
      canvasRef.current?.removeEventListener("mousemove", handleMouseMove);
      canvasRef.current?.removeEventListener("wheel", handleMouseWheel);
    };
  }, []);

  return (
    <html>
      <head>
        <title>Test</title>
        <style></style>
      </head>

      <body>
        <header>
          <div
            class="container"
            style={{
              border: "3px solid black",
              padding: "10px",
              margin: "20px",
              width: "300px",
              height: "750px",
              float: "left",
            }}
          >
            <select
              name="node_id"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select"
            >
              <option value="node_id" selected>
                node_id
              </option>
            </select>

            <select
              name="node_guid"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select2"
            >
              <option value="node_guid" selected>
                node_guid
              </option>
            </select>

            <select
              name="node_category_name"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select3"
            >
              <option value="node_category_name" selected>
                node_category_name
              </option>
            </select>

            <select
              name="node_family_name"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select4"
            >
              <option value="node_family_name" selected>
                node_family_name
              </option>
            </select>

            <select
              name="link_id"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link"
            >
              <option value="link_id" selected>
                link_id
              </option>
            </select>

            <select
              name="link_guid"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link2"
            >
              <option value="link_guid" selected>
                link_guid
              </option>
            </select>

            <select
              name="start_node_id"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link3"
            >
              <option value="start_node_id" selected>
                start_node_id
              </option>
            </select>

            <select
              name="end_node_id"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link4"
            >
              <option value="end_node_id" selected>
                end_node_id
              </option>
            </select>

            <select
              name="start_node_guid"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link5"
            >
              <option value="start_node_guid" selected>
                start_node_guid
              </option>
            </select>

            <select
              name="end_node_guid"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link6"
            >
              <option value="end_node_guid" selected>
                end_node_guid
              </option>
            </select>

            <select
              name="link_category_name"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link7"
            >
              <option value="link_category_name" selected>
                link_category_name
              </option>
            </select>

            <select
              name="link_family_name"
              style={{
                width: "200px",
                height: "50px",
              }}
              class="custom-select-link8"
            >
              <option value="link_family_name" selected>
                link_family_name
              </option>
            </select>
          </div>

          <div
            style={{
              border: "3px solid black",
              padding: "10px",
              margin: "20px",
              width: "1000px",
              height: "750px",
              float: "left",
            }}
          >
            <canvas ref={canvasRef} />
          </div>
        </header>
        <br />

        <footer>"footer"</footer>
      </body>
    </html>
  );
};

export default App;
