import React, { useContext, useState } from "react";
import {
  View,
  Text,
  TextInput,
  Pressable,
  Alert,
  Image,
  StyleSheet,
  Appearance,
} from "react-native";
import * as ImagePicker from "expo-image-picker";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { Colors } from "@/constants/Colors";
import AsyncStorage from "@react-native-async-storage/async-storage";
import SettingMenu from "../../components/SettingMenu"; // Importăm SettingMenu pentru a-l folosi în acest component
import ProgressBar from "../../components/ProgressBar"; // Importăm ProgressBar pentru a-l folosi în acest component
const colorScheme = Appearance.getColorScheme();
const theme = colorScheme === "dark" ? Colors.dark : Colors.light;
const DEFAULT_BACKGROUND = "https://www.gravatar.com/avatar/?d=mp";
const DEFAULT_AVATAR = "https://www.gravatar.com/avatar/?d=mp";

export default function ProfileScreen() {
  const { user, login, register, logout, setUser } = useContext(AuthContext);

  const [mode, setMode] = useState("login");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");

  const handleSubmit = async () => {
    try {
      if (mode === "login") {
        await login(email, password);
      } else {
        await register(email, password, username);
      }
    } catch (err) {
      Alert.alert("Eroare", err.message);
    }
  };

  const selectAndUploadImage = async () => {
    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],
      quality: 1,
    });

    if (!result.canceled) {
      const image = result.assets[0];
      const data = new FormData();
      data.append("file", {
        uri: image.uri,
        name: "avatar.jpg",
        type: "image/jpeg",
      });
      data.append("upload_preset", "avatar_upload");
      data.append("cloud_name", "dsqxeoiez");

      try {
        const res = await axios.post(
          "https://api.cloudinary.com/v1_1/dsqxeoiez/image/upload",
          data
        );
        const imageUrl = res.data.secure_url;

        await axios.put("http://192.168.100.9:8080/api/users/avatar", {
          email: user.email,
          avatarUrl: imageUrl,
        });

        await AsyncStorage.setItem("photoPath", imageUrl);
        setUser((prev) => ({ ...prev, photoPath: imageUrl }));

        Alert.alert("Succes!", "Poza de profil a fost schimbată");
      } catch (err) {
        Alert.alert("Eroare la upload", err.message);
      }
    }
  };

  const selectAndUploadBackground = async () => {
    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [3, 2],
      quality: 1,
    });

    if (!result.canceled) {
      const image = result.assets[0];
      const data = new FormData();
      data.append("file", {
        uri: image.uri,
        name: "background.jpg",
        type: "image/jpeg",
      });
      data.append("upload_preset", "avatar_upload");
      data.append("cloud_name", "dsqxeoiez");

      try {
        const res = await axios.post(
          "https://api.cloudinary.com/v1_1/dsqxeoiez/image/upload",
          data
        );
        const imageUrl = res.data.secure_url;

        await axios.put("http://192.168.100.9:8080/api/users/background", {
          email: user.email,
          backgroundPhotoUrl: imageUrl,
        });

        await AsyncStorage.setItem("backgroundPhotoPath", imageUrl);
        setUser((prev) => ({ ...prev, backgroundPhotoPath: imageUrl }));

        Alert.alert("Succes!", "Poza de fundal a fost schimbată");
      } catch (err) {
        Alert.alert("Eroare la upload", err.message);
      }
    }
  };

  const actions = [
    { label: "Logout", onPress: () => logout() },
    { label: "Schimbă fundalul", onPress: () => selectAndUploadBackground() },
    { label: "Încarcă avatar", onPress: () => selectAndUploadImage() },
  ];

  if (user) {
    return (
      <View style={styles.online_container}>
        <View style={styles.headerContainer}>
          <Image
            source={{ uri: user.backgroundPhotoPath || DEFAULT_BACKGROUND }}
            style={styles.backgroundImage}
          />

          <View style={styles.avatarWrapper}>
            <Image
              source={{ uri: user.photoPath || DEFAULT_AVATAR }}
              style={styles.avatar}
            />
          </View>
        </View>

        <View style={styles.user_container}>
          <Text style={styles.title_avatar}>{user.username}</Text>
        </View>
        <View style={styles.content}>
          <Text style={styles.text}>Matematică</Text>
          <ProgressBar score={user.scoreMath} />
          <Text></Text>
          <Text style={styles.text}>Chimie</Text>
          <ProgressBar score={user.scoreChemistry} />
        </View>
        <SettingMenu actions={actions} />
      </View>
    );
  }

  return (
    <View style={styles.login_container}>
      <Text style={styles.title}>
        {mode === "login" ? "Login" : "Register"}
      </Text>
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
      {mode === "register" && (
        <TextInput
          placeholder="Nume utilizator"
          value={username}
          onChangeText={setUsername}
          style={styles.input}
        />
      )}
      <Pressable onPress={handleSubmit} style={styles.button}>
        <Text style={styles.buttonText}>
          {mode === "login" ? "Login" : "Register"}
        </Text>
      </Pressable>
      <Text
        style={styles.link}
        onPress={() => setMode(mode === "login" ? "register" : "login")}
      >
        {mode === "login"
          ? "Nu ai cont? Înregistrează-te"
          : "Ai cont? Autentifică-te"}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: theme.background,
  },
  title: {
    fontSize: 24,
    marginBottom: 20,
    color: theme.text,
  },
  input: {
    borderWidth: 1,
    borderColor: "#ccc",
    padding: 10,
    marginBottom: 10,
    color: theme.text,
  },
  link: {
    marginTop: 20,
    color: theme.linklogin,
    fontSize: 14,
    textDecorationLine: "underline",
  },
  button: {
    backgroundColor: theme.button,
    padding: 10,
    marginTop: 10,
  },
  buttonText: {
    textAlign: "center",
    color: theme.text,
    fontSize: 16,
  },
  backgroundImage: {
    width: "100%",
    height: 200,
    resizeMode: "cover",
  },
  headerContainer: {
    position: "relative",
    width: "100%",
    height: 220,
    marginBottom: 35,
  },
  avatarWrapper: {
    position: "absolute",
    bottom: -35, // era -50, dar crește proporțional cu 120px înălțime
    left: "50%",
    transform: [{ translateX: -60 }], // -50 → -60, ca să fie centrat (jumătate din 120)
    backgroundColor: "#fff",
    borderRadius: 70, // 60 → 70, adaugă puțin extra ca să încapă bine
    padding: 4, // 4 → 6, ușor mai mare padding ca să nu se simtă înghesuit
    elevation: 6, // opțional: un pic mai mare pentru efect vizual
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 3 }, // ușor mai mare pentru avatar mai mare
    shadowOpacity: 0.3,
    shadowRadius: 10, // puțin mai mare blur
  },
  avatar: {
    width: 120,
    height: 120,
    borderRadius: 60,
  },
  user_container: {
    flex: 1,
    alignItems: "center",
    paddingTop: 20,
    backgroundColor: theme.background,
  },
  login_container: {
    flex: 1,
    padding: 20,
    top: 50,
    backgroundColor: theme.background,
  },
  title_avatar: {
    fontSize: 24,
    fontWeight: "bold",
    color: theme.text,
  },
  content: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: theme.contentBackground,
    bottom: 250,
    width: "90%",
    shadowColor: theme.shadowColor,
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    borderRadius: 10,
  },
  online_container: {
    flex: 1,
    backgroundColor: theme.background,
    alignItems: "center",
  },
  text: {
    fontSize: 18,
    color: theme.text,
    marginVertical: 10,
  },
});
