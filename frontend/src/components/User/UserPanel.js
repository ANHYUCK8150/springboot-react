import React from 'react'
import ScrollToBottom from 'react-scroll-to-bottom'

const UserPanel = ({users}) => {
  return (
    <ScrollToBottom className='messages'>
        {users != '' ? users.map((user, i) => (
        <div key={i}>
            { i == 0 ? 
                <button><h1>전체 채팅방</h1></button>
                 : 
                <button>
                    <img className='myimg'
                    src={user.imageUrl}
                    />
                    <h1>{user.name}</h1>
                </button> 
            }
        </div>
        )): ''}
    </ScrollToBottom>
  )
}

export default UserPanel