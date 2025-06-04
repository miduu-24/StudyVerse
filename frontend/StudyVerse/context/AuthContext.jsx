// context/AuthContext.jsx
import React, { createContext, useEffect, useState } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';

export const AuthContext = createContext();

const BASE_URL = 'http://192.168.1.2:8080/api/auth';
 // ← adresa ta locală


export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const load = async () => {
      const token = await AsyncStorage.getItem('token');
      const email = await AsyncStorage.getItem('email');
      const username = await AsyncStorage.getItem('username');
      if (token && email) setUser({ email, username });
    };
    load();
  }, []);

  const login = async (email, password) => {
    const res = await fetch(`${BASE_URL}/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password }),
    });

    const data = await res.json();

    if (res.ok) {
      await AsyncStorage.setItem('token', data.token);
      await AsyncStorage.setItem('email', data.user.email);
      await AsyncStorage.setItem('username', data.user.username);
      setUser({ email: data.user.email, username: data.user.username });
    } else {
      throw new Error(data.message || 'Login eșuat');
    }
  };

  const register = async (email, password, username) => {
    const res = await fetch(`${BASE_URL}/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password, username }),
    });

    if (res.ok) {
      await login(email, password); // login automat după înregistrare
    } else {
      const data = await res.json();
      throw new Error(data.message || 'Înregistrare eșuată');
    }
  };

  const logout = async () => {
    await AsyncStorage.removeItem('token');
    await AsyncStorage.removeItem('email');
    await AsyncStorage.removeItem('username');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
