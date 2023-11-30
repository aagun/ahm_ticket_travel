# AHM Ticket Travel

Aplikasi ini dibuat untuk memenuhi persyaratan techincal test kerja sebagai Fullstack Developer di PT. Astra Honda Motor

## Features

- Menambahkan daftar kota
- Merubah data kota
- Menghapus data kota
- Mencari daftar kota berdasarkan nama kota asal / kota tujuan
- Membuat transaksi tiket baru
- Merubah transaksi tiket
- Menghapus transaksi tiket
- Mencari daftar tiket bersarkan NIK / Nama penumpang

## Requirements

- [Java 8](https://www.oracle.com/java/technologies/downloads/#java8)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started/)
- [Postman](https://www.postman.com/downloads/)

## Installation

- Konfigurasi Client
    - Coming Soon
    
#### Konfigurasi Server
- Setup database PostgreSQL menggunakan docker
    ```sh
      cd ahm_ticket_travel
      docker compose up -d
    ```

- Install dependency
    ```sh
      mvn clean install
    ```

- Menjalankan aplikasi
    ```sh
      mvn spring-boot:start
    ```

- Import file ```postman-collection-api.json``` menggunakan Postman yang terdapat pada folder ```ahm_ticket_travel/src/main/resources/api-collection/```

## License

MIT

