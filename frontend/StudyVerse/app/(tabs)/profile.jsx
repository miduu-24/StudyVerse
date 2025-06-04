import React, { useContext, useState } from 'react';
import { View, Text, TextInput, Button, Alert, Pressable } from 'react-native';
import { AuthContext } from '../../context/AuthContext'; // 📌 Calea corectă față de /app/(tabs)
import { StyleSheet, Appearance} from 'react-native';
import { Colors } from '@/constants/Colors';

const colorScheme = Appearance.getColorScheme()
  
const theme = colorScheme === 'dark' ? Colors.dark : Colors.light;

export default function ProfileScreen() {

  const { user, login, register, logout } = useContext(AuthContext);

  const [mode, setMode] = useState('login');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');

  const handleSubmit = async () => {
    try {
      if (mode === 'login') {
        await login(email, password);
      } else {
        await register(email, password, username);
      }
    } catch (err) {
      Alert.alert('Eroare', err.message);
    }
  };

  if (user) {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Salut, {user.username}!</Text>
        <Pressable title="Logout" onPress={logout} style={styles.link}>
          <Text style={styles.link}>Logout</Text>
        </Pressable>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{mode === 'login' ? 'Login' : 'Register'}</Text>
      <TextInput
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        style={styles.input}
      />
      <TextInput
        placeholder="Parolă"
        secureTextEntry
        value={password}
        onChangeText={setPassword}
        style={styles.input}
      />
      {mode === 'register' && (
        <TextInput
          placeholder="Nume utilizator"
          value={username}
          onChangeText={setUsername}
          style={styles.input}
        />
      )}
      <Pressable title={mode === 'login' ? 'Login' : 'Register'} onPress={handleSubmit} style={ styles.button}>
      <Text style={styles.button}>
        {mode === 'login' ? 'Login' : 'Register'}
      </Text>
      </Pressable>
      <Text
        style={styles.link}
        onPress={() => setMode(mode === 'login' ? 'register' : 'login')}
      >
        {mode === 'login' ? 'Nu ai cont? Înregistrează-te' : 'Ai cont? Autentifică-te'}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: theme.background
  },
  title: {
    fontSize: 24,
    marginBottom: 20,
    color: theme.text,
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    marginBottom: 10,
    color: theme.text,
  },
  link: {
    marginTop: 20,
    color: theme.linklogin,
    fontSize: 14,
    textDecorationLine: 'underline',
  },
  button: {
    color: theme.text,
    fontSize: 16,
    backgroundColor: theme.button,
    padding: 5,
    textAlign: 'center',
  },
});