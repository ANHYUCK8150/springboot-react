import React from 'react'
import './Panel.css'
import LogoutButton from './LogoutButton'
import UserPanel from './UserPanel'

const Panel = ({user , users}) => {
  return (
    <div className='textContainer'>
      <div className='activeContainer line'>
        <div className='activeItem'>
            <img className='myimg'
            src={user.imageUrl}
            />
            <h1>{user.name}</h1>
        </div>
      </div>
      <div className='activeContainer'>
        <div className='activeItem'>
          <UserPanel users = {users}/>
        </div>
      </div>

    </div>
  );
};

export default Panel;
