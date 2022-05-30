import React, { useState, useEffect, useRef } from 'react'
import './Chatting.css'
import { getCurrentUser, getUserList, getAllRoom,getChatList } from '../../utils/APIUtils';
import Panel from '../User/Panel';
import Chat from '../Chat/Chat';

const Chatting = () => {
    const [users, setUsers] = useState('')
    const [user, setUser] = useState('')
    const [room, setRoom] = useState('')
    const [chatlist, setChatList] = useState('')

    useEffect(() => {
        getCurrentUser().then(response => {
            setUser(response)
        }).catch(error => {})

        getUserList().then(response => {
            setUsers(response)
        }).catch(error=>{})

        getAllRoom(0).then(response => {
            setRoom(response)
        }).catch(error=>{})

        getChatList(0).then(response => {
            setChatList(response)
        }).catch(error=>{})

    }, []);

    
  return (      
    <div className='outerContainer'>
        <Panel user={user} users={users}/>
        <Chat room = {room} user={user} chatlist={chatlist}/>
    </div>
  )
}

export default Chatting