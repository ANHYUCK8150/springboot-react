package com.study.www.auth.info;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.study.www.auth.entity.AuthProvider;
import com.study.www.auth.info.impl.FacebookOAuth2UserInfo;
import com.study.www.auth.info.impl.GoogleOAuth2UserInfo;
import com.study.www.auth.info.impl.KakaoOAuth2UserInfo;
import com.study.www.auth.info.impl.NaverOAuth2UserInfo;
import com.study.www.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws UnsupportedEncodingException {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
            return new NaverOAuth2UserInfo(attributes);
        }else if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
            return new KakaoOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(registrationId + " 로그인은 지원하지 않습니다.");
        }
    }
}
