import logo from './logo.svg';
import React from 'react'
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Chat from './components/Chatting/Chatting'
import Join from './components/Join/Join'
import OAuth2RedirectHandler from './components/oauth2/OAuth2RedirectHandler';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Join/>} />
        <Route path='/chat' element={<Chat/>} />
        <Route
        path="/oauth2/redirect"
        element={<OAuth2RedirectHandler />}
      ></Route>
      </Routes>
    </Router>
  );
}

export default App;
