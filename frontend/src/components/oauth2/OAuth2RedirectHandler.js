import React, { Component } from 'react';
import { ACCESS_TOKEN } from '../../constans';
import { Link } from '../../../node_modules/react-router-dom/index';

class OAuth2RedirectHandler extends Component {
  getUrlParameter(name) {
    /* eslint-disable */
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

    var results = regex.exec(new URL(window.location.href).search);
    return results === null
      ? ''
      : decodeURIComponent(results[1].replace(/\+/g, ' '));
  }

  render() {
    const token = this.getUrlParameter('token');
    const error = this.getUrlParameter('error');

    if (token) {
      localStorage.setItem(ACCESS_TOKEN, token);
      return (
        <Link
          to={{
            pathname: window.location.replace('/chat'),
            state: { from: this.props.location },
          }}
        />
      );
    } else {
      return (
        <Link
          to={{
            pathname: '/',
            state: {
              from: this.props.location,
              error: error,
            },
          }}
        />
      );
    }
  }
}

export default OAuth2RedirectHandler;