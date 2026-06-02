# 📦 Adaser Stock Automation System

Modern stok yönetimi süreçleri için geliştirilmiş çok katmanlı dijital stok ve yönetim sistemi.

---

# 📌 Proje Hakkında

Adaser Stock Automation System; ürün yönetimi, stok takibi, tedarikçi yönetimi ve yöneticilerin işletmeyi kontrol edebildiği Spring Boot tabanlı stok otomasyon sistemidir.

Bu proje modern web teknolojileri kullanılarak geliştirilmiştir.

---

# ✨ Temel Özellikler

## 📦 Ürün Yönetimi

* Ürün ekleme / düzenleme / silme
* Hazır ürün şablonları
* Ürün görselleri desteği
* Ürün arama sistemi
* Kategori bazlı filtreleme

---

## 📊 Stok Yönetimi

* Stok giriş işlemleri
* Stok çıkış işlemleri
* Otomatik stok güncelleme
* Stok hareket geçmişi
* Toplam stok değeri hesaplama

---

## ⚠ Kritik Stok Sistemi

* Kritik stok takibi
* Kritik stok dashboard uyarıları
* Kritik stok listesi
* Otomatik kritik ürün analizi

---

## 🏢 Tedarikçi Yönetimi

* Tedarikçi ekleme / silme
* Firma bilgileri yönetimi
* Ürün / tedarikçi ilişkileri

---

# 📈 Dashboard Özellikleri

* Günlük stok görünümü
* Toplam ürün sayısı
* Kritik stok analizi
* Son stok hareketleri
* Toplam stok değeri

---

# 🔐 Güvenlik Sistemi

Proje içerisinde rol tabanlı yetkilendirme sistemi kullanılmaktadır.


---

# 🏗 Kullanılan Yazılım Mimarisi

```text
Controller Layer

Service Layer

Repository Layer

DTO Layer

Entity Layer
```

Kullanılan mimariler:

* MVC Architecture
* Layered Architecture
* DTO Pattern
* Repository Pattern
* Exception Handling
* Validation Structure

---

# 🛠 Kullanılan Teknolojiler

* Java
* Spring Boot
* Spring Security
* Spring MVC
* Spring Data JPA
* Hibernate
* Thymeleaf
* Tailwind CSS
* Lombok
* H2 Database

---

# 📂 Proje Yapısı

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
├── templates
├── exception
├── static

```

---
# ⚙ Kurulum

## 1. Projeyi İndirin

Repository'i indirerek veya clone işlemi yaparak projeyi bilgisayarınıza alın.

```bash
git clone <repository-name>
```

## 2. Proje Klasörüne Girin

```bash
cd adaser
```

## 3. Dependency'leri Yükleyin

```bash
mvn clean install
```

## 4. Projeyi Çalıştırın

```bash
mvn spring-boot:run
```

veya IntelliJ üzerinden:

```text
Run -> StockAutomationApplication
```

## 5. Uygulamayı Açın

```text
http://localhost:8080
```

## H2 Database Console

```text
http://localhost:8080/h2-console
```





---

# 👤 Demo Kullanıcılar

## Admin

```text
username : admin

password : admin123
```

## Personel

```text
username : staff

password : admin123
```

---

# 👨‍💻 Developer

Muharrem DEMİR
