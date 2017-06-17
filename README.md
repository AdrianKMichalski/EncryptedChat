# EncryptedChat

## Wstęp
**EncryptedChat** jest aplikacją umożliwiającą szyfrowaną komunikację pomiędzy użytkownikami, zaprojektowaną by działać w środowisku rozproszonym.  

Program został stworzony na potrzeby projektu z przedmiotu Programowanie Systemów Rozproszonych.
Jego autorami są Adrian Michalski i Kamil Maj.

## Architektura
W architekturze aplikacji wyróżniamy klienta oraz usługę szyfrującą.

### Minimalna konfiguracja 
Podstawowa konfiguracja składa się z 2 instancji klienta oraz 1 usługi szyfrującej.

**Schemat działania**:
1. Użytkownik A wpisuje wiadomość oraz klika przycisk "Wyślij"
2. Klient zwraca się do usługi szyfrującej z prośbą o zaszyfrowanie wiadomości
3. Usługa szyfrująca zwraca zaszyfrowaną wiadomość do klienta
4. Klient wysyła zaszyfrowaną wiadomość do drugiego rozmówcy
5. Rozmówca odbiera wiadomość i zwraca się do usługi szyfrującej z prośbą o jej rozszyfrowanie
6. Usługa szyfrująca zwraca rozszyfrowaną wiadomość do rozmówcy
7. Rozszyfrowana wiadomość wyświetlana jest na ekranie Użytkownika B

### Konfiguracja z Load Balancerem
Konfiguracja ta zakłada połączenie z pośrednictwem usługi przekierowującej ruch do jednej z kilku instancji usługi szyfrującej. Pozwala to na poziome usługi, dzięki czemu eliminuje problemy z ograniczeniem wydajności pojedynczej maszyny, a w razie awarii jednej instancji, kolejne przejmują jej zadanie.

### Konfiguracja typu czat publiczny
W tej konfiguracji użytkownik nie podaje adresu pojedynczego rozmówcy, lecz adres na którym działa aplikacja pokoju. Podczas połączenia z pokojem następuje wpisanie użytkownika do rejestru. Po dotarciu wiadomości do pokoju rozsyłana jest ona do wszystkich użytkowników w rejestrze.


## Klient (moduł client)

### Opis
Aplikacja klienta udostępnia użytkownikowi interfejs graficzny dostępny poprzez przeglądarkę internetową. Napisana została w oparciu o framework Vaadin w wersji 8.

### Obsługa
Po uruchomieniu aplikacji należy wpisać nick (nazwę użytkownika, która wyświetlana będzie wraz z wysłaną wiadomością), adres pod którym znajduje się aplikacja klienta z której korzysta drugi rozmówca oraz adres pod którym znajduje się usługa szyfrująca.

**Przykład**:  
- Po obu stronach ekranu widoczne są 2 aplikacje klienta dziąłające pod innymi adresami.
- Po ustawieniu nicków rozmówcy ustawiają swoje adresy "na krzyż" - tj. użytkownik **A** ustawia adres użytkownika **B**, a użytkownik **B** ustawia adres użytkownika **A**.
- Obaj użytkownicy powinni używać usługi szyfrującej tego samego typu, w przeciwnym razie. Wyjątkiem od tej sytuacji może być użycie usługi tłumacza zamiast szyfrującej. W tym wypadku jeden rozmówca ustawia przykładowo usługę tłumaczącą z języka polskiego na język angielski, a drugi rozmówca usługe tłumaczącą z języka angielskiego na język polski.

### Endpoint do przyjmowania wiadomości
*TODO*


## Usługi szyfrujące (moduł cryptoservices)

### Szyfr Cezara (moduł caesar)
*TODO*

### Szyfr Morse'a (moduł morse)
*TODO*

### Szyfr Bacona (moduł bacon)
*TODO*
