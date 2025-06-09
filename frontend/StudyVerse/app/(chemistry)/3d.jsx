import React, { useEffect, useState } from "react";
import { View, Text, ActivityIndicator, StyleSheet } from "react-native";
import PeriodicScrollBar from "../../components/PeriodicScrollBar";
import Atom3DView from "@/components/Atom3DView";

export default function MoleculeViewer() {
  const [molecules, setMolecules] = useState([]);
  const [selectedMolecule, setSelectedMolecule] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://192.168.100.9:8080/api/molecules") // înlocuiește <IP> cu adresa ta reală
      .then((res) => res.json())
      .then((data) => {
        setMolecules(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Eroare la preluare molecule:", err);
        setLoading(false);
      });
  }, []);

  const handleSelect = (symbol) => {
    const found = molecules.find((m) => m.symbol === symbol);
    if (found) setSelectedMolecule(found);
  };

  return (
    <View style={styles.container}>
      {loading ? (
        <ActivityIndicator size="large" />
      ) : (
        <>
          <PeriodicScrollBar
            elements={molecules.map((m) => m.symbol)}
            onSelect={handleSelect}
          />

          {selectedMolecule && (
            <View style={styles.details}>
              <Text style={styles.title}>{selectedMolecule.name}</Text>
              <Text>Simbol: {selectedMolecule.symbol}</Text>
              <Text>Nr. atomic: {selectedMolecule.atomicNumber}</Text>
              <Text>Masa: {selectedMolecule.atomicMass}</Text>
              <Text>Electroni: {selectedMolecule.electrons}</Text>
              <Text>Shells: {selectedMolecule.electronShells}</Text>
              {selectedMolecule && (
                <>
                  <Atom3DView
                    key={selectedMolecule.symbol}
                    molecule={selectedMolecule}
                  />
                </>
              )}
            </View>
          )}
        </>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 60,
    paddingHorizontal: 16,
  },
  details: {
    marginTop: 20,
    padding: 10,
    backgroundColor: "#f2f2f2",
    borderRadius: 10,
  },
  title: {
    fontSize: 22,
    fontWeight: "bold",
    marginBottom: 6,
  },
});
