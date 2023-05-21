# Test Programmer Ibnu JA

## Prasyarat
- Java LTS 14
- Maven 3.6.x
- PostgreSQL 14

## Cara Menjalankan
1. Clone repository ini.
2. Salin `.env.example` menjadi `.env`.
3. Edit file `.env` kemudian sesuaikan konfigurasi database.
4. Buat database baru dengan nama sesuai dengan yang ada di file konfigurasi `.env` (default: `programmertest`).
5. Jalankan perintah `mvn clean install` untuk menginstall dependency.
6. Jalankan perintah `mvn spring-boot:run` untuk menjalankan aplikasi. Secara default akan jalan pada port `8080`.


## Postman Schema
Bisa diakses dengan mengklik tombol di bawah ini.
[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/10638282-5d6dd2fb-2d94-410e-a21b-eec45d486ebd?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D10638282-5d6dd2fb-2d94-410e-a21b-eec45d486ebd%26entityType%3Dcollection%26workspaceId%3D912e83d5-d50a-4966-8159-828e563cce8d)

atau mengimpor file `programmertest.postman_collection.json` ke Postman.
