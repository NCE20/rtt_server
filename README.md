# rtt_server
rtt 서버를 위한 폴더

3-tier 아키텍처로 구성되어 있습니다.

## Repository
NodeRepository.java: 노드 데이터베이스 조작을 위한 레포지토리 인터페이스

LinkRepository.java: 링크 데이터베이스 조작을 위한 레포지토리 인터페이스

## Service
NodeService.java: 노드와 관련된 비즈니스 로직을 처리하는 서비스 인터페이스

NodeServiceImpl.java: 노드와 관련된 비즈니스 로직을 구현한 서비스 클래스

LinkService.java: 링크와 관련된 비즈니스 로직을 처리하는 서비스 인터페이스

LinkServiceImpl.java: 링크와 관련된 비즈니스 로직을 구현한 서비스 클래스

## Controller
NodeController.java: 노드와 관련된 요청을 처리하는 컨트롤러 클래스

LinkController.java: 링크와 관련된 요청을 처리하는 컨트롤러 클래스

## Domain
Node.java: 노드 데이터 객체를 나타내는 도메인 클래스

Link.java: 링크 데이터 객체를 나타내는 도메인 클래스

## DTO
ObjectDto.java: 객체 데이터 전송을 위한 DTO 클래스

LinkDto.java: 링크 데이터 전송을 위한 DTO 클래스

## Util
DatabaseConnector.java: 데이터베이스 연결을 위한 유틸리티 클래스
