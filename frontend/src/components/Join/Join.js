import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { NAVER_AUTH_URL, KAKAO_AUTH_URL, ACCESS_TOKEN } from '../../constans';
import naverLogo from '../../icons/naver-logo.png';
import kakaoLogo from '../../icons/kakao-logo.png';
import './Join.css'

const Join = () => {
  const [name, setName] = useState('')
  const [room, setRoom] = useState('')
  return (
    <div className='joinOuterContainer'>
      <div className='joinInnerContainer'>
        <h1 className='heading'>로그인</h1>
        <div className="socialBox">
            <a className="social-btn" href={NAVER_AUTH_URL}>
                <img src={naverLogo} alt="Naver" /> 네이버 로그인</a>
            <a className="social-btn" href={KAKAO_AUTH_URL}>
                <img src={kakaoLogo} alt="Kakao" /> 카카오 로그인</a>
        </div>
        {/* <div>
          <input
            placeholder='이름'
            className='joinInput'
            type='text'
            onChange={(event) => setName(event.target.value)}
          />
        </div>
        <div>
          <input
            placeholder='채팅방'
            className='joinInput mt-20'
            type='text'
            onChange={(event) => setRoom(event.target.value)}
          />
        </div>
        <Link
          onClick={(e) => (!name || !room ? e.preventDefault() : null)}
          to={`/chat?name=${name}&room=${room}`}
        >
          <button className={'button mt-20'} type='submit'>
            가입
          </button>
        </Link> */}
      </div>
    </div>
  )
}

export default Join