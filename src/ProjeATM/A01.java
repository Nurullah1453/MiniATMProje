package ProjeATM;

import java.util.Scanner;

public class A01 {

    /*
    Kullanıcıdan giriş için kart numarası ve şifresini isteyin, eğer herhangi birini yanlış girerse
    tekrar isteyin.
    Kart numarası aralarda boşluk ile girilsede doğruysa kabul edin. (boşluk kabul edilebilir)
    Kart numarası ve şifre dogrulanırsa kullanıcının yapabileceği işlemleri ekrana yazdırın.

    Menü listesinde Bakiye sorgula, para yatırma, para çekme, para gönderme, şifre değiştirme ve çıkış olacaktır.

    Para çekme ve para gönderme işleminde mevcut bakiyeden daha büyük para çekilemez.
    Para gönderme işleminde istenen iban TR ile başlamalı ve toplam 26 karekterli olmalı, eğer degilse menü
    ekranına geri dönsün
    Şifre değiştirme işleminde mevcut şifreyi teyit ettikten sonra şifre değişiklik işlemi yapılmalı.
     */

    final static String kartNo="1234567890123456";
    static Scanner scan=new Scanner(System.in);
    static String sifre="1234";
    static double bakiye=20000;

    public static void main(String[] args) {


        giris();

    }

    private static void giris() {
        System.out.println("Kart Numaranızı Giriniz");
        String kKartno=scan.nextLine();
        System.out.println("Şifrenizi Giriniz");
        String kSifre=scan.nextLine();
        kKartno=kKartno.replace("\\s","");
        if (kSifre.equals(sifre)&&kKartno.equals(kartNo)){
            menu();

        }else if (!(kSifre.equals(sifre))){
            System.out.println("Yanlış Şire Girişi Yaptınız Tekrar Deneyin");
            giris();

        } else if (!(kartNo.equals(kKartno))) {
            System.out.println("Yanlış KartNo Girişi Yaptınız Tekrar Deneyin");
            giris();

        }

    }

    private static void menu() {
        System.out.println("*****NURULLAH BANKASINA HOŞGELDİNİZ*****\n" +
                "YAPMAK İSTEDİĞİNİZ İŞLEMİ SEÇİNİZ\n" +
                "\t\t 1:BAKİYE SORGULAMA\n" +
                "\t\t 2:PARA YATIRMA\n" +
                "\t\t 3:PARA ÇEKME\n" +
                "\t\t 4:PARA GÖNDERME\n" +
                "\t\t 5:ŞİFRE DEĞİŞTİRME\n" +
                "\t\t 6:ÇIKIŞ");
        int secim= scan.nextInt();

        switch (secim){
            case 1:{bakiyeSorgula();}
            case 2:
                System.out.println("YATIRILACAK MİKTARI GİRİNİZ");
                double miktar=scan.nextDouble();
            {paraYatırma(miktar);}
            case 3:{
                System.out.println("ÇEKİLECEK MİKTARI GİRİNİZ");
                double çekilecekMiktar= scan.nextDouble();
                paraCekme(çekilecekMiktar);
            }
            case 4:{
                System.out.println("İBAN GİRİNİZ");
                String iban=scan.nextLine();
                scan.nextLine();
                System.out.println("Göndermek İstediğiniz Miktarı Giriniz");
                double gönderilecekMiktar= scan.nextDouble();
               paraGonderme(ibanKontrol(iban),gönderilecekMiktar);
            }
            case 5:{
                sifreDeğiştir();
            }
            case 6:{
                cıkıs();
            }
        }




    }

    private static void cıkıs() {
        System.out.println("*******HOŞÇAKALIN*******");
        System.exit(0);
    }

    private static void sifreDeğiştir() {
        System.out.println("Şifrenizi Giriniz");
        String nSifre=scan.nextLine();
        if (sifre.equals(nSifre)){
            System.out.println("Yeni Şifrenizi Giriniz");
            sifre=scan.nextLine();
            System.out.println("Şifreniz Başarıyla Değiştirilmiştir.");
            giris();
        } else {
            System.out.println("Yanlış Şifre Girdiniz Tekrar Giriniz");
            sifreDeğiştir();
        }




    }

    private static void paraGonderme(String iban, double gönderilecekMiktar) {
        if (gönderilecekMiktar<=bakiye){
            bakiye-=gönderilecekMiktar;
            System.out.println("PARA GÖNDERME İŞLEMİNİZ BAŞARIYLA TAMAMLANMIŞTIR");
            bakiyeSorgula();
            menu();
        }else {
            System.out.println("Geçersiz Miktar Girdiniz");
            paraGonderme(iban,gönderilecekMiktar);

        }
    }

    private static String ibanKontrol(String iban) {
        iban=iban.replaceAll("\\s","");
        if (iban.startsWith("TR")&&iban.length()==26){

        }else {
            System.out.println("Geçerli İban Giriniz");
            ibanKontrol(scan.nextLine());
        }
        return iban;
    }
    

    private static void paraCekme(double çekilecekMiktar) {
        if (çekilecekMiktar<=bakiye){
            bakiye-=çekilecekMiktar;
            System.out.println("Para Çekme İşleminiz Gerçekleşmiştir");
            bakiyeSorgula();
            menu();
        }else {
            System.out.println("Bakiyeniz Yetersiz Geçerli Miktar Giriniz");
            paraCekme(scan.nextDouble());
        }
    }

    private static void paraYatırma(double miktar) {
        bakiye+=miktar;
        System.out.println("Yatırdığınız "+miktar+" TL Başarıyla Hesabınıza Yatırılmıştır");

        bakiyeSorgula();
        menu();
    }

    private static void bakiyeSorgula() {
        System.out.println("Güncel Bakiyeniz: " + bakiye+" TL");
        menu();
    }
}
