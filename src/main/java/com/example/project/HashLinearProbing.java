package com.example.project;

import java.util.Random;

public class HashLinearProbing {
      private int hsize; // tamano de la tabla hash
    private Persona[] buckets; // array que representa la tabla hash
    private Persona AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.AVAILABLE = null;
        this.size = 0;
    }

    public int hashing(String dni) {
        
        int hash = 0;
        
        for (int i = 0; i < dni.length(); i++) {
            hash = 7 * i + hash; 
        }
        
        //El modulo fue elegido basado en el tamaño del hash no en un numero primo
        hash = hash % hsize;
        
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    public void insertHash(Persona key) {
        
        
        int hash = hashing(key.DNI);

        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] == null ) {
                buckets[hash] = key;
                size++;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(String dni) {
        int hash = hashing(dni);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] != null && buckets[hash].DNI.equals(dni)) {
                buckets[hash] = AVAILABLE;
                size--;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + dni + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            
            //AVAILABLE REDUNDANTE, pero 
            if (buckets[i] == null || buckets[i] == AVAILABLE) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public Persona findHash(String dni) {
        int hash = hashing(dni);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return null;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (buckets[hash].DNI.equals(dni)) {
//                    Sobreescribe el null (?)
//                    buckets[hash] = AVAILABLE;
                    return buckets[hash];
                }
            } catch (Exception E) {
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + dni + " no encontrada!");
        return null;
    }    
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

    public static void main (String[] args){
        HashLinearProbing tb = new HashLinearProbing(10);

        Random rd = new Random();

        for(int i = 0; i < 5; i++){
            
        }

        tb.displayHashtable();        
    }
}
