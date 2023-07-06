const express = require('express');
const mysql = require('mysql');

const app = express();

// MySQL 데이터베이스 연결 설정
const connection = mysql.createConnection({
  host: '',     // 데이터베이스 호스트
  user: '',  // 데이터베이스 사용자 이름
  password: '',  // 데이터베이스 비밀번호
  database: ''   // 데이터베이스 이름
});

// 데이터베이스 연결
connection.connect((err) => {
  if (err) {
    console.error('Database connection failed: ', err);
  } else {
    console.log('Connected to the database');
  }
});

// 서버 시작
app.listen(8000, () => {
  console.log('Server is running on port 8000');
});