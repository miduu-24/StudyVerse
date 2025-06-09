import React, { useRef, useState } from "react";
import { View, Button, PanResponder } from "react-native";
import { GLView } from "expo-gl";
import { Renderer } from "expo-three";
import * as THREE from "three";

export default function Atom3DView({ molecule }) {
  const rotation = useRef({ x: 0, y: 0 });
  const zoom = useRef(6);
  const cameraRef = useRef(null);
  const initialTouches = useRef([]);
  const electronShellsRef = useRef([]);
  const animationActive = useRef(true);
  const [dummy, setDummy] = useState(false); // for re-rendering after button toggle

  const toggleAnimation = () => {
    animationActive.current = !animationActive.current;
    setDummy((d) => !d);
  };

  const panResponder = useRef(
    PanResponder.create({
      onMoveShouldSetPanResponder: () => true,
      onPanResponderGrant: (e) => {
        initialTouches.current = e.nativeEvent.touches;
      },
      onPanResponderMove: (e, gesture) => {
        const touches = e.nativeEvent.touches;
        if (touches.length === 1) {
          rotation.current.y += gesture.dx * 0.001;
          rotation.current.x += gesture.dy * 0.001;
        } else if (touches.length === 2) {
          const [touch1, touch2] = touches;
          const dx = touch1.pageX - touch2.pageX;
          const dy = touch1.pageY - touch2.pageY;
          const distance = Math.sqrt(dx * dx + dy * dy);

          const [prevTouch1, prevTouch2] = initialTouches.current;
          if (prevTouch1 && prevTouch2) {
            const prevDx = prevTouch1.pageX - prevTouch2.pageX;
            const prevDy = prevTouch1.pageY - prevTouch2.pageY;
            const prevDistance = Math.sqrt(prevDx * prevDx + prevDy * prevDy);

            const scaleDiff = distance - prevDistance;
            zoom.current = Math.max(
              2,
              Math.min(20, zoom.current - scaleDiff * 0.005)
            );
            if (cameraRef.current) {
              cameraRef.current.position.z = zoom.current;
            }
          }
        }
      },
      onPanResponderRelease: () => {
        initialTouches.current = [];
      },
    })
  ).current;

  const renderScene = async (gl) => {
    const { drawingBufferWidth: width, drawingBufferHeight: height } = gl;
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(75, width / height, 0.1, 1000);
    camera.position.z = zoom.current;
    cameraRef.current = camera;

    const renderer = new Renderer({ gl });
    renderer.setSize(width, height);

    const ambientLight = new THREE.AmbientLight(0xffffff);
    scene.add(ambientLight);

    const nucleus = new THREE.Mesh(
      new THREE.SphereGeometry(0.4, 32, 32),
      new THREE.MeshStandardMaterial({ color: 0xff4444 })
    );
    scene.add(nucleus);

    const electronShells = JSON.parse(molecule.electronShells);
    const shellRadiusStart = 1.5;
    const shellColors = ["#1E90FF", "#32CD32", "#FF8C00", "#8A2BE2", "#FF1493"];

    electronShellsRef.current = [];

    electronShells.forEach((count, index) => {
      const radius = shellRadiusStart + index * 0.8;
      const color = shellColors[index % shellColors.length];
      const electrons = [];

      for (let i = 0; i < count; i++) {
        const angle = (2 * Math.PI * i) / count;
        const electron = new THREE.Mesh(
          new THREE.SphereGeometry(0.1, 16, 16),
          new THREE.MeshStandardMaterial({ color })
        );
        electron.userData = { angle, radius, speed: 0.01 + index * 0.003 };
        electron.position.set(
          Math.cos(angle) * radius,
          Math.sin(angle) * radius,
          0
        );
        scene.add(electron);
        electrons.push(electron);
      }

      const circle = new THREE.LineLoop(
        new THREE.BufferGeometry().setFromPoints(
          Array.from({ length: 100 }, (_, i) => {
            const theta = (i / 100) * 2 * Math.PI;
            return new THREE.Vector3(
              Math.cos(theta) * radius,
              Math.sin(theta) * radius,
              0
            );
          })
        ),
        new THREE.LineBasicMaterial({ color })
      );
      scene.add(circle);

      electronShellsRef.current.push(electrons);
    });

    const animate = () => {
      scene.rotation.y = rotation.current.y;
      scene.rotation.x = rotation.current.x;

      if (animationActive.current) {
        electronShellsRef.current.forEach((shell) => {
          shell.forEach((electron) => {
            electron.userData.angle += electron.userData.speed;
            const { angle, radius } = electron.userData;
            electron.position.set(
              Math.cos(angle) * radius,
              Math.sin(angle) * radius,
              0
            );
          });
        });
      }

      renderer.render(scene, camera);
      gl.endFrameEXP();
      requestAnimationFrame(animate);
    };

    animate();
  };

  return (
    <View style={{ height: 360 }}>
      <Button
        title={animationActive.current ? "🔴 Stop" : "🟢 Start"}
        onPress={toggleAnimation}
      />
      <View style={{ height: 300 }} {...panResponder.panHandlers}>
        <GLView style={{ flex: 1 }} onContextCreate={renderScene} />
      </View>
    </View>
  );
}
