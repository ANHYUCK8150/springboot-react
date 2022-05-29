import React, { useState, useEffect, useRef } from 'react'
import { useLocation  } from 'react-router-dom';
import './Chat.css'
import InfoBar from '../InfoBar/InfoBar'
import Messages from '../Messages/Messages'
import { getCurrentUser } from '../../utils/APIUtils';
import * as StompJs from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import onlineIcon from '../../icons/onlineIcon.png'
import closeIcon from '../../icons/closeIcon.png'
import { ACCESS_TOKEN } from '../../constans';
import { Link } from '../../../node_modules/react-router-dom/index';
const ENDPOINT = 'http://localhost:8090/ws'

const Chat = ({room , user}) => {
  
  const client = useRef({});
  const [chatMessages, setChatMessages] = useState([]);

  const {search } = useLocation()
  const [message, setMessage] = useState('')
  const [messages, setMessages] = useState('')
  const [name ,setName] = useState('')

  useEffect(() => {
    connect()
    return () => disconnect()
  }, []);

  const connect = () => {
    client.current = new StompJs.Client({
      // brokerURL: "ws://localhost:8080/ws-stomp/websocket", // 웹소켓 서버로 직접 접속
      webSocketFactory: () => new SockJS(ENDPOINT), // proxy를 통한 접속
      connectHeaders: {
        "auth-token": "spring-chat-auth-token",
      },
      debug: function (str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        subscribe();
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });

    client.current.activate();
  };

  const disconnect = () => {
    client.current.deactivate();
  };

  const subscribe = () => {
    client.current.subscribe(`/queue/addChatToClient/${room.id}`, ({ body }) => {
      setMessages((_chatMessages) => [..._chatMessages, JSON.parse(body)]);
    });
  };

  const publish = (message) => {
    if (!client.current.connected) {
      return;
    }

    if(message != ""){
      client.current.publish({
        destination: "/app/chat",
        body: JSON.stringify({ message: message, user:user, room:room }),
      });
    }

    setMessage("");
  };

  const doLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    window.location.replace('/')
  };

  return (
    
      <div className='container'>
        <div className='infoBar'>
          <div className='leftInnerContainer'>
            <img className='onlineIcon' src={onlineIcon} alt='online icon' />
              {room.id == 0 ?
                <h3>전체 채팅방</h3>
                :
                <h3>{user.name}</h3>
              }
          </div>
          <div className='rightInnerContainer'>
            <a href='javascript:void(0);' onClick={doLogout}>
              <img src={closeIcon} alt='close icon' />
            </a>
          </div>
        </div>
        <Messages messages={messages} name={user.name} />
        <div className='form'>
          <input
            className='input'
            type={"text"}
            placeholder={"message"}
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            onKeyPress={(e) => e.which === 13 && publish(message)}
          />
          <button className='sendButton' onClick={() => publish(message)}>send</button>
        </div>
      </div>
  )
}

export default Chat