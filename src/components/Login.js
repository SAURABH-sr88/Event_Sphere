import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function Login(){
  const [username,setUsername] = useState('');
  const [password,setPassword] = useState('');
  const nav = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('/api/auth/login',{ username, password });
      localStorage.setItem('token', res.data.token);
      alert('Logged in');
      nav('/');
    } catch (err) {
      alert('Invalid credentials');
    }
  }

  return (<div>
    <h2>Login</h2>
    <form onSubmit={submit}>
      <input placeholder="username" value={username} onChange={e=>setUsername(e.target.value)} /><br/>
      <input type="password" placeholder="password" value={password} onChange={e=>setPassword(e.target.value)} /><br/>
      <button type="submit">Login</button>
    </form>
  </div>);
}
