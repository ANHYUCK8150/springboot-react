import React, { useState, useEffect } from 'react'
import { useLocation  } from 'react-router-dom';
import queryString from 'query-string'
import io from 'socket.io-client'
import './Chat.css'
import InfoBar from '../InfoBar/InfoBar'
import Messages from '../Messages/Messages'
import Input from '../Input/Input'
import TextContainer from '../TextContainer/TextContainer'
import SockJS from 'sockjs-client';
import Stomp from "stompjs";
const ENDPOINT = 'http://localhost:8090/ws'
//let socket;

const Chat = () => {
    
  const {search } = useLocation()
  const [users, setUsers] = useState('')
  const [message, setMessage] = useState('')
  const [messages, setMessages] = useState([])

  const { name, room } = queryString.parse(search)

  let sock = new SockJS('http://localhost:8090/ws')
  let client = Stomp.over(sock);
  client.debug = null;

  useEffect(() => {
    const content = "어서와";
    const sender = name;
    client.connect({}, () =>{
        //client.send("/app/join", {},JSON.stringify(name))

        // Create Message
        
        //client.send(`/app/chat`,{},JSON.stringify({ content, sender }))

        // client.subscribe('/queue/addChatToClient/'+name, function(messageDTO){
        //     const messagedto = JSON.parse(messageDTO.body)
        // })
    })  
    return () => client.disconnect();

}, [client])
  // useEffect(() => {
  //   const { name, room } = queryString.parse(search)
  //   socket = io(ENDPOINT)

  //   setRoom(room)
  //   setName(name)
  //   socket.emit('/app/join', { name, room }, (error) => {
  //     if (error) {
  //       alert(error)
  //     }
  //   })
  // }, [ENDPOINT, search])
  // useEffect(() => {
  //   //로딩 될때만 실행
  //   socket.on('message', (message) => {
  //     setMessage((message) => [...messages, message])
  //   })
  //   socket.on('roomData', ({ users }) => {
  //     setUsers(users)
  //   })
  // }, [])
  const sendMessage = (event) => {
    event.preventDefault()
    console.log("보내자");
    const content = message;
    const sender = name;
    if (message) {
        waitForConnection(client,function(){
          client.send(`/app/chat`,{},JSON.stringify({ content, sender }))
        })
    }
  }
  const waitForConnection = (stompClient, callback) => {
    setTimeout(
        function () {
            // 연결되었을 때 콜백함수 실행
            if (stompClient.ws.readyState === 1) {
                callback();
                // 연결이 안 되었으면 재호출
            } else {
                waitForConnection(stompClient, callback);
            }
        },
        1 // 밀리초 간격으로 실행
    );
  }
  return (
    <div className='outerContainer'>
      <div className='container'>
        <InfoBar room={room} />
        <Messages messages={messages} name={name} />
        <Input
          message={message}
          setMessage={setMessage}
          sendMessage={sendMessage}
        />
      </div>
      <TextContainer users={users} />
    </div>
  )
}

export default Chat