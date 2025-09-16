import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function Register(){
  const [username,setUsername] = useState('');
  const [email,setEmail] = useState('');
  const [password,setPassword] = useState('');
  const nav = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('/api/auth/register',{ username, email, password });
      localStorage.setItem('token', res.data.token);
      alert('Registered');
      nav('/');
    } catch (err) {
      alert('Error registering');
    }
  }

  return (<div>
    <h2>Register</h2>
    <form onSubmit={submit}>
      <input placeholder="username" value={username} onChange={e=>setUsername(e.target.value)} /><br/>
      <input placeholder="email" value={email} onChange={e=>setEmail(e.target.value)} /><br/>
      <input type="password" placeholder="password" value={password} onChange={e=>setPassword(e.target.value)} /><br/>
      <button type="submit">Register</button>
    </form>
  </div>);
}
