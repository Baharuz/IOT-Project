# IOT-Project: Akıllı Ev Otomasyon Sistemi 🏠🌐

Bu proje, modern bir akıllı ev ekosistemini simüle eden, Java diliyle geliştirilmiş **Nesne Yönelimli Programlama (OOP)** tabanlı bir otomasyon sistemidir. Sistem; sensörlerin ve cihazların merkezi bir birimden yönetilmesini, gerçek zamanlı veri akışını ve dinamik enerji stratejilerini kapsar.

## 🎯 Projenin Amacı
Sistem; farklı sensörlerin (sıcaklık, duman, hareket) ve cihazların (klima, lamba, alarm) tek bir merkezden (**Hub**) yönetilmesini, cihazlar arası iletişimi ve farklı enerji stratejilerine göre sistemin dinamik olarak tepki vermesini sağlar.

---

## 🛠 Kullanılan Tasarım Kalıpları ve Teknikler

Proje mimarisi, sürdürülebilir ve genişletilebilir bir yapı için popüler **Design Patterns** kullanılarak inşa edilmiştir:

* **Factory Pattern:** `DeviceFactory` sınıfı üzerinden cihaz nesneleri merkezi olarak üretilir. Bu sayede sisteme yeni cihaz türleri eklemek mevcut kodu bozmadan (Open/Closed Principle) gerçekleştirilir.
* **Observer Pattern:** `SmartHomeHub` sınıfı, cihazları bir liste içinde tutar ve değişimleri tüm cihazlara eşzamanlı olarak iletir. Bu, "loosely coupled" (gevşek bağlı) bir mimari sağlar.
* **Strategy Pattern:** `IEnergyStrategy` arayüzü ile sistem çalışma anında (**Runtime**) "Comfort Mode" veya "Eco Mode" gibi farklı enerji politikalarına geçiş yapabilir.
* **Multithreading:** Sensörler bağımsız iş parçacıkları (Threads) olarak çalışır. Hub verileri dinlerken, sensörler arka planda eşzamanlı olarak veri üretmeye devam eder.
* **Exception Handling:** Sisteme özel `PilBittiException` ve `SensorArizasiException` gibi hata sınıflarıyla, olası arıza durumları güvenli hale getirilmiştir.
* **Inner Classes & Interfaces:** Temel davranışlar `IDevice` gibi arayüzlerle tanımlanmış, kod düzeni için dahili sınıflardan yararlanılmıştır.

---

## ⚙️ Çalışma Akışı

1.  **Başlatma:** Merkezi Hub birimi oluşturulur.
2.  **Üretim & Kayıt:** `DeviceFactory` ile üretilen cihazlar (Klima, Duman Sensörü vb.) Hub sistemine kaydedilir.
3.  **Simülasyon:** Sistem "Comfort Mode" ile başlar, sensörler eşzamanlı veri üretir. Ardından otomatik olarak "Eco Mode" stratejisine geçiş yapılır.
4.  **Hata Yönetimi:** Pil tüketimi simüle edilerek, pilin bitme anı ve otomatik şarj mekanizması test edilir.
5.  **Kapanış:** Tüm sensörler durdurulur ve final raporu sunulur.

---

## 📂 Proje Modülleri ve Paket Yapısı

| Modül (Paket) | Açıklama |
| :--- | :--- |
| **hub** | Sistemin beyni; cihaz yönetimini ve strateji değişimlerini koordine eder. |
| **devices** | Sıcaklık, Hareket, Duman sensörleri ile Lamba, Klima ve Alarm sınıflarını içerir. |
| **factory** | Cihaz üretim mantığını ve `DeviceType` yönetimini barındırır. |
| **strategy** | Enerji yönetimi algoritmalarını (Comfort/Eco) içerir. |
| **interfaces** | `IDevice`, `ISensor`, `IObserver` gibi temel kuralları tanımlar. |
| **abstract_classes** | Ortak özelliklerin (ID, marka vb.) tekrarını önleyen soyut sınıfları içerir. |
| **exceptions** | Pil bitmesi ve arıza senaryolarına özel hata sınıflarını içerir. |

---

## 📊 Sistem Etkileşimi
Kullanıcı merkezi arayüz üzerinden stratejileri yönetirken; sensörler asenkron olarak veri üretir ve Hub bu verileri işleyerek otomasyon senaryolarını (örneğin: yangın durumunda alarmın çalması) devreye alır.
