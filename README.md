# My Labs
## _Tehnici de programare multiprocesor 2021_
[![N|Solid](https://plati-taxe.uaic.ro/img/logo-retina1.png)](https://www.info.uaic.ro/)

Croitoru Razvan 3A2
Diac P. Gabriel 3A2



## Tema 1

### Continut
- [x] [Exercitiu 1](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/README.md#exercitiu-1)
- [x] [Exercitiu 2a](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/README.md#exercitiu-2a)
- [x] [Exercitiu 2b](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/README.md#exercitiu-2b)
- [x] [Exercitiu 2c](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/README.md#exercitiu-2b)
- [x] [Exercitiu 3a](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/README.md#a)
- [x] [Exercitiu 3b](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/README.md#b)
### Exercitiu 1
[![N|Solid](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_1/Fig1.PNG)](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_1/Fig1.PNG)

Istoria de executie este liniarizabila, putand alege punctele de liniarizare intr-un mod valid,care produce o executie corecta, precum in diagrama de mai sus. Cum istoria de executie este liniarizabila, atunci ea este automat si consistent secventiala,
intrucat putem obtine o ordonare totala.

Putem rescrie ordinea de executie ca:
```
                r = 0   ✔️
B r.write(1)    r = 1   ✔️
A r.read():1    r = 1   ✔️
A r.read():1    r = 1   ✔️
C r.write(2)    r = 2   ✔️
B r.read():2    r = 2   ✔️
B r.read():2    r = 2   ✔️
C r.write(1)    r = 1   ✔️
A r.read():1    r = 1   ✔️
```

### Exercitiu 2a

Metoda [ExecutionRun.consumersGetStuckBecauseOfCaching()](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/e1604524994a0a2336623920fa5a5f2853292685/Homework1_2a/src/main/java/Demonstration/ExecutionRun.java#L60) 
demonstreaza ca generalizarea nu functioneaza din cauza optimizarilor de compilare.

Procesul de executie este descris prin urmatorii pasi:

**Pas A**. Toti consumatorii incearca sa consume inainte sa existe ceva in coada, deci o sa se blocheze
in bucla `while(tail - head == QSIZE){}` din `deq()`

Aceasta problema poate fi simulata prin adaugarea unui sleep dupa startul consumatorilor, fix inainte
de startul producatorilor, astfel incat consumatorii sa fie in bucla cand primul producator porneste.

**Pas B**. Pornesc producatorii si se intampla urmatoarele:

- Producatorii sunt singurii care mai functioneaza si executia lor se incheie in mod normal, terminandu-si
de inserat tot ce aveau de inserat (presupunand ca nu vor incerca sa insereze mai mult decat capacitatea cozii).
- Consumatorii raman blocati in bucla `while` din cauza cache-uirii variabilelor `tail` si `head`.  

---
<details>
    <summary>
    Output-ul problemei de caching (din care se observa ca programul s-a blocat)
    </summary>
[1636394594158] Consumer  Consumer1 waiting before while, with tail 0, head 0  

[1636394594205] Consumer  Consumer2 waiting before while, with tail 0, head 0

Thread  Producer2 started: Enqueue(1) of queue: []

[1636394594247] Producer  Producer2 waiting before while, with tail 0, head 0, try to push x=1

[1636394594248] Producer  Producer2 ABOUT to locks -> tail 0, head 0, try to push x=1

[1636394594253] Producer  Producer2 is has LOCKED -> tail 0, head 0, try to push x=1

[1636394594258] Producer  Producer2 has ENQUEUED VALUE -> tail 1, head 0, try to push x=1

[1636394594259] Producer  Producer2 is ABOUT to unlock -> tail 1, head 0, try to push x=1

[1636394594260] Producer  Producer2 has UNLOCKED -> tail 1, head 0, try to push x=1

Thread  Producer2 ended  : Enqueue(1) of queue: [1]



Thread  Producer2 started: Enqueue(2) of queue: [1]

[1636394594265] Producer  Producer2 waiting before while, with tail 1, head 0, try to push x=2

[1636394594266] Producer  Producer2 ABOUT to locks -> tail 1, head 0, try to push x=2

[1636394594267] Producer  Producer2 is has LOCKED -> tail 1, head 0, try to push x=2

[1636394594268] Producer  Producer2 has ENQUEUED VALUE -> tail 2, head 0, try to push x=2

[1636394594269] Producer  Producer2 is ABOUT to unlock -> tail 2, head 0, try to push x=2

[1636394594271] Producer  Producer2 has UNLOCKED -> tail 2, head 0, try to push x=2

Thread  Producer2 ended  : Enqueue(2) of queue: [1, 2]



Thread  Producer2 started: Enqueue(3) of queue: [1, 2]

[1636394594273] Producer  Producer2 waiting before while, with tail 2, head 0, try to push x=3

[1636394594275] Producer  Producer2 ABOUT to locks -> tail 2, head 0, try to push x=3

[1636394594276] Producer  Producer2 is has LOCKED -> tail 2, head 0, try to push x=3

[1636394594277] Producer  Producer2 has ENQUEUED VALUE -> tail 3, head 0, try to push x=3

[1636394594277] Producer  Producer2 is ABOUT to unlock -> tail 3, head 0, try to push x=3

[1636394594278] Producer  Producer2 has UNLOCKED -> tail 3, head 0, try to push x=3

Thread  Producer2 ended  : Enqueue(3) of queue: [1, 2, 3]



Thread  Producer2 started: Enqueue(4) of queue: [1, 2, 3]

[1636394594280] Producer  Producer2 waiting before while, with tail 3, head 0, try to push x=4

[1636394594281] Producer  Producer2 ABOUT to locks -> tail 3, head 0, try to push x=4

[1636394594282] Producer  Producer2 is has LOCKED -> tail 3, head 0, try to push x=4

[1636394594283] Producer  Producer2 has ENQUEUED VALUE -> tail 4, head 0, try to push x=4

[1636394594283] Producer  Producer2 is ABOUT to unlock -> tail 4, head 0, try to push x=4

[1636394594284] Producer  Producer2 has UNLOCKED -> tail 4, head 0, try to push x=4

Thread  Producer2 ended  : Enqueue(4) of queue: [1, 2, 3, 4]



Thread  Producer2 started: Enqueue(5) of queue: [1, 2, 3, 4]

[1636394594285] Producer  Producer2 waiting before while, with tail 4, head 0, try to push x=5

[1636394594286] Producer  Producer2 ABOUT to locks -> tail 4, head 0, try to push x=5

[1636394594288] Producer  Producer2 is has LOCKED -> tail 4, head 0, try to push x=5

[1636394594290] Producer  Producer2 has ENQUEUED VALUE -> tail 5, head 0, try to push x=5

[1636394594290] Producer  Producer2 is ABOUT to unlock -> tail 5, head 0, try to push x=5

[1636394594291] Producer  Producer2 has UNLOCKED -> tail 5, head 0, try to push x=5

Thread  Producer2 ended  : Enqueue(5) of queue: [1, 2, 3, 4, 5]



Thread  Producer2 started: Enqueue(6) of queue: [1, 2, 3, 4, 5]

[1636394594293] Producer  Producer2 waiting before while, with tail 5, head 0, try to push x=6

[1636394594294] Producer  Producer2 ABOUT to locks -> tail 5, head 0, try to push x=6

[1636394594295] Producer  Producer2 is has LOCKED -> tail 5, head 0, try to push x=6

[1636394594296] Producer  Producer2 has ENQUEUED VALUE -> tail 6, head 0, try to push x=6

[1636394594299] Producer  Producer2 is ABOUT to unlock -> tail 6, head 0, try to push x=6

[1636394594300] Producer  Producer2 has UNLOCKED -> tail 6, head 0, try to push x=6

Thread  Producer2 ended  : Enqueue(6) of queue: [1, 2, 3, 4, 5, 6]



Thread  Producer2 started: Enqueue(7) of queue: [1, 2, 3, 4, 5, 6]

[1636394594302] Producer  Producer2 waiting before while, with tail 6, head 0, try to push x=7

[1636394594302] Producer  Producer2 ABOUT to locks -> tail 6, head 0, try to push x=7

[1636394594303] Producer  Producer2 is has LOCKED -> tail 6, head 0, try to push x=7

[1636394594304] Producer  Producer2 has ENQUEUED VALUE -> tail 7, head 0, try to push x=7

[1636394594305] Producer  Producer2 is ABOUT to unlock -> tail 7, head 0, try to push x=7

[1636394594306] Producer  Producer2 has UNLOCKED -> tail 7, head 0, try to push x=7

Thread  Producer2 ended  : Enqueue(7) of queue: [1, 2, 3, 4, 5, 6, 7]



Thread  Producer2 started: Enqueue(8) of queue: [1, 2, 3, 4, 5, 6, 7]

[1636394594308] Producer  Producer2 waiting before while, with tail 7, head 0, try to push x=8

[1636394594315] Producer  Producer2 ABOUT to locks -> tail 7, head 0, try to push x=8

[1636394594316] Producer  Producer2 is has LOCKED -> tail 7, head 0, try to push x=8

[1636394594317] Producer  Producer2 has ENQUEUED VALUE -> tail 8, head 0, try to push x=8

[1636394594317] Producer  Producer2 is ABOUT to unlock -> tail 8, head 0, try to push x=8

[1636394594318] Producer  Producer2 has UNLOCKED -> tail 8, head 0, try to push x=8

Thread  Producer2 ended  : Enqueue(8) of queue: [1, 2, 3, 4, 5, 6, 7, 8]



Thread  Producer2 started: Enqueue(9) of queue: [1, 2, 3, 4, 5, 6, 7, 8]

[1636394594320] Producer  Producer2 waiting before while, with tail 8, head 0, try to push x=9

[1636394594321] Producer  Producer2 ABOUT to locks -> tail 8, head 0, try to push x=9

[1636394594321] Producer  Producer2 is has LOCKED -> tail 8, head 0, try to push x=9

[1636394594322] Producer  Producer2 has ENQUEUED VALUE -> tail 9, head 0, try to push x=9

[1636394594335] Producer  Producer2 is ABOUT to unlock -> tail 9, head 0, try to push x=9

[1636394594336] Producer  Producer2 has UNLOCKED -> tail 9, head 0, try to push x=9

Thread  Producer2 ended  : Enqueue(9) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9]



Thread  Producer2 started: Enqueue(10) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9]

Thread  Producer1 started: Enqueue(1) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9]

[1636394594541] Producer  Producer2 waiting before while, with tail 9, head 0, try to push x=10

[1636394594541] Producer  Producer1 waiting before while, with tail 9, head 0, try to push x=1

[1636394594548] Producer  Producer1 ABOUT to locks -> tail 9, head 0, try to push x=1

[1636394594549] Producer  Producer1 is has LOCKED -> tail 9, head 0, try to push x=1

[1636394594550] Producer  Producer1 has ENQUEUED VALUE -> tail 10, head 0, try to push x=1

[1636394594551] Producer  Producer1 is ABOUT to unlock -> tail 10, head 0, try to push x=1

[1636394594548] Producer  Producer2 ABOUT to locks -> tail 9, head 0, try to push x=10

[1636394594551] Producer  Producer1 has UNLOCKED -> tail 10, head 0, try to push x=1

Thread  Producer1 ended  : Enqueue(1) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1]



Thread  Producer1 started: Enqueue(2) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1]

[1636394594554] Producer  Producer1 waiting before while, with tail 10, head 0, try to push x=2

[1636394594554] Producer  Producer1 ABOUT to locks -> tail 10, head 0, try to push x=2

[1636394594555] Producer  Producer1 is has LOCKED -> tail 10, head 0, try to push x=2

[1636394594556] Producer  Producer1 has ENQUEUED VALUE -> tail 11, head 0, try to push x=2

[1636394594556] Producer  Producer1 is ABOUT to unlock -> tail 11, head 0, try to push x=2

[1636394594557] Producer  Producer1 has UNLOCKED -> tail 11, head 0, try to push x=2

Thread  Producer1 ended  : Enqueue(2) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2]



Thread  Producer1 started: Enqueue(3) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2]

[1636394594559] Producer  Producer1 waiting before while, with tail 11, head 0, try to push x=3

[1636394594564] Producer  Producer1 ABOUT to locks -> tail 11, head 0, try to push x=3

[1636394594564] Producer  Producer1 is has LOCKED -> tail 11, head 0, try to push x=3

[1636394594565] Producer  Producer1 has ENQUEUED VALUE -> tail 12, head 0, try to push x=3

[1636394594566] Producer  Producer1 is ABOUT to unlock -> tail 12, head 0, try to push x=3

[1636394594566] Producer  Producer1 has UNLOCKED -> tail 12, head 0, try to push x=3

Thread  Producer1 ended  : Enqueue(3) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3]



Thread  Producer1 started: Enqueue(4) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3]

[1636394594568] Producer  Producer1 waiting before while, with tail 12, head 0, try to push x=4

[1636394594568] Producer  Producer1 ABOUT to locks -> tail 12, head 0, try to push x=4

[1636394594568] Producer  Producer1 is has LOCKED -> tail 12, head 0, try to push x=4

[1636394594569] Producer  Producer1 has ENQUEUED VALUE -> tail 13, head 0, try to push x=4

[1636394594569] Producer  Producer1 is ABOUT to unlock -> tail 13, head 0, try to push x=4

[1636394594569] Producer  Producer1 has UNLOCKED -> tail 13, head 0, try to push x=4

Thread  Producer1 ended  : Enqueue(4) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4]



Thread  Producer1 started: Enqueue(5) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4]

[1636394594571] Producer  Producer1 waiting before while, with tail 13, head 0, try to push x=5

[1636394594572] Producer  Producer1 ABOUT to locks -> tail 13, head 0, try to push x=5

[1636394594572] Producer  Producer1 is has LOCKED -> tail 13, head 0, try to push x=5

[1636394594573] Producer  Producer1 has ENQUEUED VALUE -> tail 14, head 0, try to push x=5

[1636394594574] Producer  Producer1 is ABOUT to unlock -> tail 14, head 0, try to push x=5

[1636394594574] Producer  Producer1 has UNLOCKED -> tail 14, head 0, try to push x=5

Thread  Producer1 ended  : Enqueue(5) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5]



Thread  Producer1 started: Enqueue(6) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5]

[1636394594575] Producer  Producer1 waiting before while, with tail 14, head 0, try to push x=6

[1636394594576] Producer  Producer1 ABOUT to locks -> tail 14, head 0, try to push x=6

[1636394594576] Producer  Producer1 is has LOCKED -> tail 14, head 0, try to push x=6

[1636394594577] Producer  Producer1 has ENQUEUED VALUE -> tail 15, head 0, try to push x=6

[1636394594578] Producer  Producer1 is ABOUT to unlock -> tail 15, head 0, try to push x=6

[1636394594579] Producer  Producer1 has UNLOCKED -> tail 15, head 0, try to push x=6

Thread  Producer1 ended  : Enqueue(6) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6]



Thread  Producer1 started: Enqueue(7) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6]

[1636394594580] Producer  Producer1 waiting before while, with tail 15, head 0, try to push x=7

[1636394594580] Producer  Producer1 ABOUT to locks -> tail 15, head 0, try to push x=7

[1636394594580] Producer  Producer1 is has LOCKED -> tail 15, head 0, try to push x=7

[1636394594581] Producer  Producer1 has ENQUEUED VALUE -> tail 16, head 0, try to push x=7

[1636394594582] Producer  Producer1 is ABOUT to unlock -> tail 16, head 0, try to push x=7

[1636394594582] Producer  Producer1 has UNLOCKED -> tail 16, head 0, try to push x=7

Thread  Producer1 ended  : Enqueue(7) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7]



Thread  Producer1 started: Enqueue(8) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7]

[1636394594585] Producer  Producer1 waiting before while, with tail 16, head 0, try to push x=8

[1636394594586] Producer  Producer1 ABOUT to locks -> tail 16, head 0, try to push x=8

[1636394594587] Producer  Producer1 is has LOCKED -> tail 16, head 0, try to push x=8

[1636394594587] Producer  Producer1 has ENQUEUED VALUE -> tail 17, head 0, try to push x=8

[1636394594589] Producer  Producer1 is ABOUT to unlock -> tail 17, head 0, try to push x=8

[1636394594589] Producer  Producer1 has UNLOCKED -> tail 17, head 0, try to push x=8

Thread  Producer1 ended  : Enqueue(8) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8]



Thread  Producer1 started: Enqueue(9) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8]

[1636394594591] Producer  Producer1 waiting before while, with tail 17, head 0, try to push x=9

[1636394594592] Producer  Producer1 ABOUT to locks -> tail 17, head 0, try to push x=9

[1636394594592] Producer  Producer1 is has LOCKED -> tail 17, head 0, try to push x=9

[1636394594594] Producer  Producer1 has ENQUEUED VALUE -> tail 18, head 0, try to push x=9

[1636394594594] Producer  Producer1 is ABOUT to unlock -> tail 18, head 0, try to push x=9

[1636394594595] Producer  Producer1 has UNLOCKED -> tail 18, head 0, try to push x=9

Thread  Producer1 ended  : Enqueue(9) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9]



Thread  Producer1 started: Enqueue(10) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9]

[1636394594597] Producer  Producer1 waiting before while, with tail 18, head 0, try to push x=10

[1636394594598] Producer  Producer1 ABOUT to locks -> tail 18, head 0, try to push x=10

[1636394594598] Producer  Producer1 is has LOCKED -> tail 18, head 0, try to push x=10

[1636394594599] Producer  Producer1 has ENQUEUED VALUE -> tail 19, head 0, try to push x=10

[1636394594600] Producer  Producer1 is ABOUT to unlock -> tail 19, head 0, try to push x=10

[1636394594600] Producer  Producer1 has UNLOCKED -> tail 19, head 0, try to push x=10

Thread  Producer1 ended  : Enqueue(10) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]



Thread  Producer1 finished...

[1636394594614] Producer  Producer2 is has LOCKED -> tail 19, head 0, try to push x=10

[1636394594614] Producer  Producer2 has ENQUEUED VALUE -> tail 20, head 0, try to push x=10

[1636394594614] Producer  Producer2 is ABOUT to unlock -> tail 20, head 0, try to push x=10

[1636394594614] Producer  Producer2 has UNLOCKED -> tail 20, head 0, try to push x=10

Thread  Producer2 ended  : Enqueue(10) of queue: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10]



Thread  Producer2 finished...
</details>

---

Problema cache-uirii se poate rezolva prin folosirea keyword-ului `volatile` pentru `tail` si `head` sau prin
utilizarea variabilelor in interiorul buclei.  
ex:
```java
while(tail - head == QSIZE) { 
    System.out.printf(“%d, %d”, tail, head);
} 
```

Odata cu rezolvarea problemei de caching, algoritmul generalizat functioneaza.
Se presupune ca head si tail sunt declarate ca volatile.
Folosirea al aceluiasi lacat pentru sectiuni diferite inseamna 2 sectiuni critice blocate si mutual exclusive privitor la ambele tipuri de thread-uri (consumator, producator). Astfel cand un producator blocheaza sectiunea critica din `enq`, consumtorii nu vor putea intra in sectiunea critica din `deq`.

Acest lucru asigura in acelasi timp ca algoritmul va functiona fara conflicte intre consumatori, si fara conflicte intre producatori. Algoritmul initial pentru un singur consumator si un singur 1 producator nu putea suporta mai multe thread-uri din cauza problemelor generate de executarea concurenta a secvectele de cod:

  1. `enq()` (Exemple de probleme: OutOfBoundExeption, un producator poate suprascrie operatia altui producator)
    
    ````java
    		items [ tail % QSIZE ] = x; 
    		tail ++;
    ````
    
  2. `deq()`
    ```java
    int item = items [ head % QSIZE ]; 
    head ++;
    ````
    
  Prin adaugarea lacatului pe aceste sectiuni se rezolva problemele de mai sus si algoritmul va functiona.
  Observati: 
  1.	lacatul intodeauna va avea count-ul 1, pentru ca, per apel de metoda (end sau deq), lacatul este blocat o singura data de thread-ul care primeste primul lock, dupa care deblocat o singura data (consumatorul apeleaza doar deq(), iar producatorul doar enq())
  2.	algoritmul nu este fair
  
  <details>
    <summary>
    Output-ul cu evitarea evitarea problemelor de optimizare
    </summary>
    [1636479869794] Producer Producer-1 waiting before while, with tail 0, head 0, try to push x=1 

[1636479869795] Producer Producer-1 ABOUT to locks -> tail 0, head 0, try to push x=1 ------ holdCount=0 

Thread Producer-6 started: Enqueue(1) of queue: []

[1636479869796] Producer Producer-6 waiting before while, with tail 0, head 0, try to push x=1 

[1636479869797] Producer Producer-6 ABOUT to locks -> tail 0, head 0, try to push x=1 ------ holdCount=0 

[1636479869798] Producer Producer-1 is has LOCKED -> tail 0, head 0, try to push x=1 ------ holdCount=1 

[1636479869798] Producer Producer-1 has ENQUEUED VALUE -> tail 1, head 0, try to push x=1 ------ holdCount=1 

[1636479869799] Producer Producer-1 is ABOUT to unlock -> tail 1, head 0, try to push x=1 ------ holdCount=1 

[1636479869799] Producer Producer-1 has UNLOCKED -> tail 1, head 0, try to push x=1 ------ holdCount=0 

Thread Producer-1 ended : Enqueue(1) of queue: [1]

Thread Producer-7 started: Enqueue(1) of queue: [1]

[1636479869801] Producer Producer-7 waiting before while, with tail 1, head 0, try to push x=1 

[1636479869801] Producer Producer-7 ABOUT to locks -> tail 1, head 0, try to push x=1 ------ holdCount=0 

[1636479869802] Producer Producer-7 is has LOCKED -> tail 1, head 0, try to push x=1 ------ holdCount=1 

[1636479869802] Producer Producer-7 has ENQUEUED VALUE -> tail 2, head 0, try to push x=1 ------ holdCount=1 

[1636479869804] Producer Producer-7 is ABOUT to unlock -> tail 2, head 0, try to push x=1 ------ holdCount=1 

[1636479869804] Producer Producer-7 has UNLOCKED -> tail 2, head 0, try to push x=1 ------ holdCount=0 

Thread Producer-7 ended : Enqueue(1) of queue: [1, 1]

Thread Producer-7 started: Enqueue(2) of queue: [1, 1]

[1636479869806] Producer Producer-7 waiting before while, with tail 2, head 0, try to push x=2 

[1636479869806] Producer Producer-7 ABOUT to locks -> tail 2, head 0, try to push x=2 ------ holdCount=0 

[1636479869806] Producer Producer-7 is has LOCKED -> tail 2, head 0, try to push x=2 ------ holdCount=1 

[1636479869807] Producer Producer-7 has ENQUEUED VALUE -> tail 3, head 0, try to push x=2 ------ holdCount=1 

[1636479869808] Producer Producer-7 is ABOUT to unlock -> tail 3, head 0, try to push x=2 ------ holdCount=1 

[1636479869808] Producer Producer-7 has UNLOCKED -> tail 3, head 0, try to push x=2 ------ holdCount=0 

Thread Producer-7 ended : Enqueue(2) of queue: [1, 1, 2]

Thread Producer-7 started: Enqueue(3) of queue: [1, 1, 2]

[1636479869809] Producer Producer-7 waiting before while, with tail 3, head 0, try to push x=3 

[1636479869810] Producer Producer-7 ABOUT to locks -> tail 3, head 0, try to push x=3 ------ holdCount=0 

[1636479869810] Producer Producer-7 is has LOCKED -> tail 3, head 0, try to push x=3 ------ holdCount=1 

[1636479869811] Producer Producer-7 has ENQUEUED VALUE -> tail 4, head 0, try to push x=3 ------ holdCount=1 

[1636479869811] Producer Producer-7 is ABOUT to unlock -> tail 4, head 0, try to push x=3 ------ holdCount=1 

[1636479869812] Producer Producer-7 has UNLOCKED -> tail 4, head 0, try to push x=3 ------ holdCount=0 

Thread Producer-7 ended : Enqueue(3) of queue: [1, 1, 2, 3]

Thread Producer-7 finished.

.

.

Thread Producer-3 started: Enqueue(1) of queue: [1, 1, 2, 3]

[1636479869813] Producer Producer-3 waiting before while, with tail 4, head 0, try to push x=1 

[1636479869814] Producer Producer-3 ABOUT to locks -> tail 4, head 0, try to push x=1 ------ holdCount=0 

[1636479869800] Consumer Consumer-5 waiting before while, with tail 1, head 0 ------ holdCount=0 

[1636479869815] Consumer Consumer-5 BEFORE lock!

with tail 4, head 0 ------ holdCount=0 

[1636479869815] Consumer Consumer-2 waiting before while, with tail 4, head 0 ------ holdCount=0 

[1636479869816] Consumer Consumer-2 BEFORE lock!

with tail 4, head 0 ------ holdCount=0 

[1636479869796] Consumer Consumer-6 waiting before while, with tail 0, head 0 ------ holdCount=0 

[1636479869817] Consumer Consumer-6 BEFORE lock!

with tail 4, head 0 ------ holdCount=0 

Thread Producer-2 started: Enqueue(1) of queue: [1, 1, 2, 3]

[1636479869903] Producer Producer-2 waiting before while, with tail 4, head 0, try to push x=1 

[1636479869914] Producer Producer-2 ABOUT to locks -> tail 4, head 0, try to push x=1 ------ holdCount=0 

[1636479869812] Consumer Consumer-3 waiting before while, with tail 4, head 0 ------ holdCount=0 

[1636479869933] Consumer Consumer-3 BEFORE lock!

with tail 4, head 0 ------ holdCount=0 

[1636479869812] Producer Producer-6 is has LOCKED -> tail 4, head 0, try to push x=1 ------ holdCount=1 

[1636479869935] Producer Producer-6 has ENQUEUED VALUE -> tail 5, head 0, try to push x=1 ------ holdCount=1 

[1636479869937] Producer Producer-6 is ABOUT to unlock -> tail 5, head 0, try to push x=1 ------ holdCount=1 

[1636479869940] Producer Producer-6 has UNLOCKED -> tail 5, head 0, try to push x=1 ------ holdCount=0 

Thread Producer-6 ended : Enqueue(1) of queue: [1, 1, 2, 3, 1]

Thread Producer-6 started: Enqueue(2) of queue: [1, 1, 2, 3, 1]

[1636479869966] Producer Producer-6 waiting before while, with tail 5, head 0, try to push x=2 

[1636479869967] Producer Producer-6 ABOUT to locks -> tail 5, head 0, try to push x=2 ------ holdCount=0 

[1636479869999] Producer Producer-6 is has LOCKED -> tail 5, head 0, try to push x=2 ------ holdCount=1 

[1636479870012] Producer Producer-6 has ENQUEUED VALUE -> tail 6, head 0, try to push x=2 ------ holdCount=1 

[1636479870013] Producer Producer-6 is ABOUT to unlock -> tail 6, head 0, try to push x=2 ------ holdCount=1 

[1636479870015] Producer Producer-6 has UNLOCKED -> tail 6, head 0, try to push x=2 ------ holdCount=0 

Thread Producer-6 ended : Enqueue(2) of queue: [1, 1, 2, 3, 1, 2]

Thread Producer-6 started: Enqueue(3) of queue: [1, 1, 2, 3, 1, 2]

[1636479870025] Producer Producer-6 waiting before while, with tail 6, head 0, try to push x=3 

[1636479870027] Producer Producer-6 ABOUT to locks -> tail 6, head 0, try to push x=3 ------ holdCount=0 

[1636479870028] Producer Producer-6 is has LOCKED -> tail 6, head 0, try to push x=3 ------ holdCount=1 

[1636479870029] Producer Producer-6 has ENQUEUED VALUE -> tail 7, head 0, try to push x=3 ------ holdCount=1 

[1636479870038] Producer Producer-6 is ABOUT to unlock -> tail 7, head 0, try to push x=3 ------ holdCount=1 

[1636479870039] Producer Producer-6 has UNLOCKED -> tail 7, head 0, try to push x=3 ------ holdCount=0 

Thread Producer-6 ended : Enqueue(3) of queue: [1, 1, 2, 3, 1, 2, 3]

Thread Producer-6 finished.

.

.

Thread Producer-4 started: Enqueue(1) of queue: [1, 1, 2, 3, 1, 2, 3]

[1636479870042] Producer Producer-4 waiting before while, with tail 7, head 0, try to push x=1 

[1636479870069] Producer Producer-4 ABOUT to locks -> tail 7, head 0, try to push x=1 ------ holdCount=0 

[1636479870070] Producer Producer-4 is has LOCKED -> tail 7, head 0, try to push x=1 ------ holdCount=1 

[1636479870071] Producer Producer-4 has ENQUEUED VALUE -> tail 8, head 0, try to push x=1 ------ holdCount=1 

[1636479870072] Producer Producer-4 is ABOUT to unlock -> tail 8, head 0, try to push x=1 ------ holdCount=1 

[1636479870073] Producer Producer-4 has UNLOCKED -> tail 8, head 0, try to push x=1 ------ holdCount=0 

Thread Producer-4 ended : Enqueue(1) of queue: [1, 1, 2, 3, 1, 2, 3, 1]

Thread Producer-4 started: Enqueue(2) of queue: [1, 1, 2, 3, 1, 2, 3, 1]

[1636479870078] Producer Producer-4 waiting before while, with tail 8, head 0, try to push x=2 

[1636479870079] Producer Producer-4 ABOUT to locks -> tail 8, head 0, try to push x=2 ------ holdCount=0 

[1636479870079] Producer Producer-4 is has LOCKED -> tail 8, head 0, try to push x=2 ------ holdCount=1 

[1636479870080] Producer Producer-4 has ENQUEUED VALUE -> tail 9, head 0, try to push x=2 ------ holdCount=1 

[1636479870081] Producer Producer-4 is ABOUT to unlock -> tail 9, head 0, try to push x=2 ------ holdCount=1 

[1636479870082] Producer Producer-4 has UNLOCKED -> tail 9, head 0, try to push x=2 ------ holdCount=0 

Thread Producer-4 ended : Enqueue(2) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2]

Thread Producer-4 started: Enqueue(3) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2]

[1636479870084] Producer Producer-4 waiting before while, with tail 9, head 0, try to push x=3 

[1636479870085] Producer Producer-4 ABOUT to locks -> tail 9, head 0, try to push x=3 ------ holdCount=0 

[1636479870086] Producer Producer-4 is has LOCKED -> tail 9, head 0, try to push x=3 ------ holdCount=1 

[1636479870089] Producer Producer-4 has ENQUEUED VALUE -> tail 10, head 0, try to push x=3 ------ holdCount=1 

[1636479870090] Producer Producer-4 is ABOUT to unlock -> tail 10, head 0, try to push x=3 ------ holdCount=1 

[1636479870091] Producer Producer-4 has UNLOCKED -> tail 10, head 0, try to push x=3 ------ holdCount=0 

Thread Producer-4 ended : Enqueue(3) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2, 3]

Thread Producer-4 finished.

.

.

[1636479870096] Producer Producer-3 is has LOCKED -> tail 10, head 0, try to push x=1 ------ holdCount=1 

[1636479870430] Producer Producer-3 has ENQUEUED VALUE -> tail 11, head 0, try to push x=1 ------ holdCount=1 

[1636479870431] Producer Producer-3 is ABOUT to unlock -> tail 11, head 0, try to push x=1 ------ holdCount=1 

[1636479870432] Producer Producer-3 has UNLOCKED -> tail 11, head 0, try to push x=1 ------ holdCount=0 

Thread Producer-3 ended : Enqueue(1) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1]

Thread Producer-3 started: Enqueue(2) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1]

[1636479870440] Producer Producer-3 waiting before while, with tail 11, head 0, try to push x=2 

[1636479870441] Producer Producer-3 ABOUT to locks -> tail 11, head 0, try to push x=2 ------ holdCount=0 

[1636479870442] Producer Producer-3 is has LOCKED -> tail 11, head 0, try to push x=2 ------ holdCount=1 

[1636479870444] Producer Producer-3 has ENQUEUED VALUE -> tail 12, head 0, try to push x=2 ------ holdCount=1 

[1636479870445] Producer Producer-3 is ABOUT to unlock -> tail 12, head 0, try to push x=2 ------ holdCount=1 

[1636479870446] Producer Producer-3 has UNLOCKED -> tail 12, head 0, try to push x=2 ------ holdCount=0 

Thread Producer-3 ended : Enqueue(2) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2]

Thread Producer-3 started: Enqueue(3) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2]

[1636479870448] Producer Producer-3 waiting before while, with tail 12, head 0, try to push x=3 

[1636479870449] Producer Producer-3 ABOUT to locks -> tail 12, head 0, try to push x=3 ------ holdCount=0 

[1636479870451] Producer Producer-3 is has LOCKED -> tail 12, head 0, try to push x=3 ------ holdCount=1 

[1636479870454] Producer Producer-3 has ENQUEUED VALUE -> tail 13, head 0, try to push x=3 ------ holdCount=1 

[1636479870454] Producer Producer-3 is ABOUT to unlock -> tail 13, head 0, try to push x=3 ------ holdCount=1 

[1636479870455] Producer Producer-3 has UNLOCKED -> tail 13, head 0, try to push x=3 ------ holdCount=0 

Thread Producer-3 ended : Enqueue(3) of queue: [1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3]

Thread Producer-3 finished.

.

.

[1636479869807] Consumer Consumer-4 waiting before while, with tail 2, head 0 ------ holdCount=0 

[1636479870461] Consumer Consumer-4 BEFORE lock!

with tail 13, head 0 ------ holdCount=0 

[1636479870462] Consumer Consumer-4 has LOCKED!

with tail 13, head 0 ------ holdCount=1 

[1636479870463] Consumer Consumer-4 EXTRACTED item 1 with tail 13, head 1 ------ holdCount=1 

[1636479870463] Consumer Consumer-4 ABOUT to unlock!

with tail 13, head 1 ------ holdCount=1 

[1636479870464] Consumer Consumer-4 has UNLOCKED!

with tail 13, head 1 ------ holdCount=0 

[1636479870464] Consumer Consumer-5 has LOCKED!

with tail 13, head 1 ------ holdCount=1 

[1636479870465] Consumer Consumer-5 EXTRACTED item 1 with tail 13, head 2 ------ holdCount=1 

[1636479870465] Consumer Consumer-5 ABOUT to unlock!

with tail 13, head 2 ------ holdCount=1 

[1636479870465] Consumer Consumer-5 has UNLOCKED!

with tail 13, head 2 ------ holdCount=0 

[1636479870466] Consumer Consumer-5 waiting before while, with tail 13, head 2 ------ holdCount=0 

[1636479870467] Consumer Consumer-5 BEFORE lock!

with tail 13, head 2 ------ holdCount=0 

[1636479870468] Consumer Consumer-5 has LOCKED!

with tail 13, head 2 ------ holdCount=1 

[1636479870469] Consumer Consumer-5 EXTRACTED item 2 with tail 13, head 3 ------ holdCount=1 

[1636479870469] Consumer Consumer-5 ABOUT to unlock!

with tail 13, head 3 ------ holdCount=1 

[1636479870470] Consumer Consumer-5 has UNLOCKED!

with tail 13, head 3 ------ holdCount=0 

[1636479870471] Consumer Consumer-5 waiting before while, with tail 13, head 3 ------ holdCount=0 

[1636479870472] Consumer Consumer-5 BEFORE lock!

with tail 13, head 3 ------ holdCount=0 

[1636479870472] Consumer Consumer-5 has LOCKED!

with tail 13, head 3 ------ holdCount=1 

[1636479870473] Consumer Consumer-5 EXTRACTED item 3 with tail 13, head 4 ------ holdCount=1 

[1636479870474] Consumer Consumer-5 ABOUT to unlock!

with tail 13, head 4 ------ holdCount=1 

[1636479870475] Consumer Consumer-5 has UNLOCKED!

with tail 13, head 4 ------ holdCount=0 

Thread Consumer-5 finished.

.

.

Thread Producer-1 started: Enqueue(2) of queue: [1, 2, 3, 1, 2, 3, 1, 2, 3]

[1636479870476] Producer Producer-1 waiting before while, with tail 13, head 4, try to push x=2 

[1636479870477] Producer Producer-1 ABOUT to locks -> tail 13, head 4, try to push x=2 ------ holdCount=0 

[1636479870478] Producer Producer-1 is has LOCKED -> tail 13, head 4, try to push x=2 ------ holdCount=1 

[1636479870479] Producer Producer-1 has ENQUEUED VALUE -> tail 14, head 4, try to push x=2 ------ holdCount=1 

[1636479870479] Producer Producer-1 is ABOUT to unlock -> tail 14, head 4, try to push x=2 ------ holdCount=1 

[1636479870480] Producer Producer-1 has UNLOCKED -> tail 14, head 4, try to push x=2 ------ holdCount=0 

Thread Producer-1 ended : Enqueue(2) of queue: [1, 2, 3, 1, 2, 3, 1, 2, 3, 2]

Thread Producer-1 started: Enqueue(3) of queue: [1, 2, 3, 1, 2, 3, 1, 2, 3, 2]

[1636479870483] Producer Producer-1 waiting before while, with tail 14, head 4, try to push x=3 

[1636479870484] Producer Producer-1 ABOUT to locks -> tail 14, head 4, try to push x=3 ------ holdCount=0 

Thread Producer-5 started: Enqueue(1) of queue: [1, 2, 3, 1, 2, 3, 1, 2, 3, 2]

[1636479870485] Producer Producer-5 waiting before while, with tail 14, head 4, try to push x=1 

[1636479870486] Producer Producer-5 ABOUT to locks -> tail 14, head 4, try to push x=1 ------ holdCount=0 

[1636479870484] Consumer Consumer-2 has LOCKED!

with tail 14, head 4 ------ holdCount=1 

[1636479870487] Consumer Consumer-2 EXTRACTED item 1 with tail 14, head 5 ------ holdCount=1 

[1636479870488] Consumer Consumer-2 ABOUT to unlock!

with tail 14, head 5 ------ holdCount=1 

[1636479870488] Consumer Consumer-2 has UNLOCKED!

with tail 14, head 5 ------ holdCount=0 

[1636479870464] Consumer Consumer-4 waiting before while, with tail 13, head 1 ------ holdCount=0 

[1636479870495] Consumer Consumer-4 BEFORE lock!

with tail 14, head 5 ------ holdCount=0 

[1636479870496] Consumer Consumer-4 has LOCKED!

with tail 14, head 5 ------ holdCount=1 

[1636479870496] Consumer Consumer-4 EXTRACTED item 2 with tail 14, head 6 ------ holdCount=1 

[1636479870497] Consumer Consumer-4 ABOUT to unlock!

with tail 14, head 6 ------ holdCount=1 

[1636479870498] Consumer Consumer-4 has UNLOCKED!

with tail 14, head 6 ------ holdCount=0 

[1636479870501] Consumer Consumer-4 waiting before while, with tail 14, head 6 ------ holdCount=0 

[1636479870501] Consumer Consumer-4 BEFORE lock!

with tail 14, head 6 ------ holdCount=0 

[1636479870501] Consumer Consumer-2 waiting before while, with tail 14, head 6 ------ holdCount=0 

[1636479870503] Consumer Consumer-2 BEFORE lock!

with tail 14, head 6 ------ holdCount=0 

[1636479869819] Consumer Consumer-10 waiting before while, with tail 4, head 0 ------ holdCount=0 

[1636479870504] Consumer Consumer-10 BEFORE lock!

with tail 14, head 6 ------ holdCount=0 

Thread Producer-10 started: Enqueue(1) of queue: [3, 1, 2, 3, 1, 2, 3, 2]

[1636479870524] Producer Producer-10 waiting before while, with tail 14, head 6, try to push x=1 

[1636479870524] Producer Producer-10 ABOUT to locks -> tail 14, head 6, try to push x=1 ------ holdCount=0 

Thread Producer-9 started: Enqueue(1) of queue: [3, 1, 2, 3, 1, 2, 3, 2]

[1636479870525] Producer Producer-9 waiting before while, with tail 14, head 6, try to push x=1 

[1636479870544] Producer Producer-9 ABOUT to locks -> tail 14, head 6, try to push x=1 ------ holdCount=0 

[1636479869818] Consumer Consumer-9 waiting before while, with tail 4, head 0 ------ holdCount=0 

[1636479870556] Consumer Consumer-9 BEFORE lock!

with tail 14, head 6 ------ holdCount=0 

[1636479869818] Consumer Consumer-8 waiting before while, with tail 4, head 0 ------ holdCount=0 

[1636479870557] Consumer Consumer-8 BEFORE lock!

with tail 14, head 6 ------ holdCount=0 

[1636479869818] Consumer Consumer-7 waiting before while, with tail 4, head 0 ------ holdCount=0 

[1636479870567] Consumer Consumer-7 BEFORE lock!

with tail 14, head 6 ------ holdCount=0 

Thread Producer-8 started: Enqueue(1) of queue: [3, 1, 2, 3, 1, 2, 3, 2]

[1636479870568] Producer Producer-8 waiting before while, with tail 14, head 6, try to push x=1 

[1636479870569] Producer Producer-8 ABOUT to locks -> tail 14, head 6, try to push x=1 ------ holdCount=0 

[1636479869818] Consumer Consumer-1 waiting before while, with tail 4, head 0 ------ holdCount=0 

[1636479870573] Consumer Consumer-1 BEFORE lock!

with tail 14, head 6 ------ holdCount=0 

[1636479870501] Consumer Consumer-6 has LOCKED!

with tail 14, head 6 ------ holdCount=1 

[1636479870574] Consumer Consumer-6 EXTRACTED item 3 with tail 14, head 7 ------ holdCount=1 

[1636479870575] Consumer Consumer-6 ABOUT to unlock!

with tail 14, head 7 ------ holdCount=1 

[1636479870576] Consumer Consumer-6 has UNLOCKED!

with tail 14, head 7 ------ holdCount=0 

[1636479870582] Consumer Consumer-6 waiting before while, with tail 14, head 7 ------ holdCount=0 

[1636479870582] Consumer Consumer-6 BEFORE lock!

with tail 14, head 7 ------ holdCount=0 

[1636479870582] Producer Producer-2 is has LOCKED -> tail 14, head 7, try to push x=1 ------ holdCount=1 

[1636479870584] Producer Producer-2 has ENQUEUED VALUE -> tail 15, head 7, try to push x=1 ------ holdCount=1 

[1636479870585] Producer Producer-2 is ABOUT to unlock -> tail 15, head 7, try to push x=1 ------ holdCount=1 

[1636479870585] Producer Producer-2 has UNLOCKED -> tail 15, head 7, try to push x=1 ------ holdCount=0 

Thread Producer-2 ended : Enqueue(1) of queue: [1, 2, 3, 1, 2, 3, 2, 1]

Thread Producer-2 started: Enqueue(2) of queue: [1, 2, 3, 1, 2, 3, 2, 1]

[1636479870590] Producer Producer-2 waiting before while, with tail 15, head 7, try to push x=2 

[1636479870590] Producer Producer-2 ABOUT to locks -> tail 15, head 7, try to push x=2 ------ holdCount=0 

[1636479870591] Producer Producer-2 is has LOCKED -> tail 15, head 7, try to push x=2 ------ holdCount=1 

[1636479870592] Producer Producer-2 has ENQUEUED VALUE -> tail 16, head 7, try to push x=2 ------ holdCount=1 

[1636479870592] Producer Producer-2 is ABOUT to unlock -> tail 16, head 7, try to push x=2 ------ holdCount=1 

[1636479870598] Producer Producer-2 has UNLOCKED -> tail 16, head 7, try to push x=2 ------ holdCount=0 

Thread Producer-2 ended : Enqueue(2) of queue: [1, 2, 3, 1, 2, 3, 2, 1, 2]

Thread Producer-2 started: Enqueue(3) of queue: [1, 2, 3, 1, 2, 3, 2, 1, 2]

[1636479870601] Producer Producer-2 waiting before while, with tail 16, head 7, try to push x=3 

[1636479870601] Producer Producer-2 ABOUT to locks -> tail 16, head 7, try to push x=3 ------ holdCount=0 

[1636479870602] Producer Producer-2 is has LOCKED -> tail 16, head 7, try to push x=3 ------ holdCount=1 

[1636479870605] Producer Producer-2 has ENQUEUED VALUE -> tail 17, head 7, try to push x=3 ------ holdCount=1 

[1636479870606] Producer Producer-2 is ABOUT to unlock -> tail 17, head 7, try to push x=3 ------ holdCount=1 

[1636479870606] Producer Producer-2 has UNLOCKED -> tail 17, head 7, try to push x=3 ------ holdCount=0 

Thread Producer-2 ended : Enqueue(3) of queue: [1, 2, 3, 1, 2, 3, 2, 1, 2, 3]

Thread Producer-2 finished.

.

.

[1636479870608] Consumer Consumer-3 has LOCKED!

with tail 17, head 7 ------ holdCount=1 

[1636479870609] Consumer Consumer-3 EXTRACTED item 1 with tail 17, head 8 ------ holdCount=1 

[1636479870609] Consumer Consumer-3 ABOUT to unlock!

with tail 17, head 8 ------ holdCount=1 

[1636479870612] Consumer Consumer-3 has UNLOCKED!

with tail 17, head 8 ------ holdCount=0 

[1636479870614] Consumer Consumer-3 waiting before while, with tail 17, head 8 ------ holdCount=0 

[1636479870615] Consumer Consumer-3 BEFORE lock!

with tail 17, head 8 ------ holdCount=0 

[1636479870616] Consumer Consumer-3 has LOCKED!

with tail 17, head 8 ------ holdCount=1 

[1636479870616] Consumer Consumer-3 EXTRACTED item 2 with tail 17, head 9 ------ holdCount=1 

[1636479870617] Consumer Consumer-3 ABOUT to unlock!

with tail 17, head 9 ------ holdCount=1 

[1636479870617] Consumer Consumer-3 has UNLOCKED!

with tail 17, head 9 ------ holdCount=0 

[1636479870617] Consumer Consumer-3 waiting before while, with tail 17, head 9 ------ holdCount=0 

[1636479870618] Consumer Consumer-3 BEFORE lock!

with tail 17, head 9 ------ holdCount=0 

[1636479870617] Producer Producer-1 is has LOCKED -> tail 17, head 9, try to push x=3 ------ holdCount=1 

[1636479870624] Producer Producer-1 has ENQUEUED VALUE -> tail 18, head 9, try to push x=3 ------ holdCount=1 

[1636479870625] Producer Producer-1 is ABOUT to unlock -> tail 18, head 9, try to push x=3 ------ holdCount=1 

[1636479870626] Producer Producer-1 has UNLOCKED -> tail 18, head 9, try to push x=3 ------ holdCount=0 

Thread Producer-1 ended : Enqueue(3) of queue: [3, 1, 2, 3, 2, 1, 2, 3, 3]

Thread Producer-1 finished.

.

.

[1636479870703] Producer Producer-5 is has LOCKED -> tail 18, head 9, try to push x=1 ------ holdCount=1 

[1636479870704] Producer Producer-5 has ENQUEUED VALUE -> tail 19, head 9, try to push x=1 ------ holdCount=1 

[1636479870706] Producer Producer-5 is ABOUT to unlock -> tail 19, head 9, try to push x=1 ------ holdCount=1 

[1636479870712] Producer Producer-5 has UNLOCKED -> tail 19, head 9, try to push x=1 ------ holdCount=0 

Thread Producer-5 ended : Enqueue(1) of queue: [3, 1, 2, 3, 2, 1, 2, 3, 3, 1]

Thread Producer-5 started: Enqueue(2) of queue: [3, 1, 2, 3, 2, 1, 2, 3, 3, 1]

[1636479870714] Producer Producer-5 waiting before while, with tail 19, head 9, try to push x=2 

[1636479870715] Producer Producer-5 ABOUT to locks -> tail 19, head 9, try to push x=2 ------ holdCount=0 

[1636479870716] Producer Producer-5 is has LOCKED -> tail 19, head 9, try to push x=2 ------ holdCount=1 

[1636479870716] Producer Producer-5 has ENQUEUED VALUE -> tail 20, head 9, try to push x=2 ------ holdCount=1 

[1636479870724] Producer Producer-5 is ABOUT to unlock -> tail 20, head 9, try to push x=2 ------ holdCount=1 

[1636479870725] Producer Producer-5 has UNLOCKED -> tail 20, head 9, try to push x=2 ------ holdCount=0 

Thread Producer-5 ended : Enqueue(2) of queue: [3, 1, 2, 3, 2, 1, 2, 3, 3, 1, 2]

Thread Producer-5 started: Enqueue(3) of queue: [3, 1, 2, 3, 2, 1, 2, 3, 3, 1, 2]

[1636479870728] Producer Producer-5 waiting before while, with tail 20, head 9, try to push x=3 

[1636479870729] Producer Producer-5 ABOUT to locks -> tail 20, head 9, try to push x=3 ------ holdCount=0 

[1636479870729] Producer Producer-5 is has LOCKED -> tail 20, head 9, try to push x=3 ------ holdCount=1 

[1636479870729] Producer Producer-5 has ENQUEUED VALUE -> tail 21, head 9, try to push x=3 ------ holdCount=1 

[1636479870729] Producer Producer-5 is ABOUT to unlock -> tail 21, head 9, try to push x=3 ------ holdCount=1 

[1636479870729] Producer Producer-5 has UNLOCKED -> tail 21, head 9, try to push x=3 ------ holdCount=0 

Thread Producer-5 ended : Enqueue(3) of queue: [3, 1, 2, 3, 2, 1, 2, 3, 3, 1, 2, 3]

Thread Producer-5 finished.

.

.

[1636479870736] Consumer Consumer-4 has LOCKED!

with tail 21, head 9 ------ holdCount=1 

[1636479870736] Consumer Consumer-4 EXTRACTED item 3 with tail 21, head 10 ------ holdCount=1 

[1636479870737] Consumer Consumer-4 ABOUT to unlock!

with tail 21, head 10 ------ holdCount=1 

[1636479870737] Consumer Consumer-4 has UNLOCKED!

with tail 21, head 10 ------ holdCount=0 

Thread Consumer-4 finished.

.

.

[1636479870739] Consumer Consumer-2 has LOCKED!

with tail 21, head 10 ------ holdCount=1 

[1636479870740] Consumer Consumer-2 EXTRACTED item 1 with tail 21, head 11 ------ holdCount=1 

[1636479870740] Consumer Consumer-2 ABOUT to unlock!

with tail 21, head 11 ------ holdCount=1 

[1636479870740] Consumer Consumer-2 has UNLOCKED!

with tail 21, head 11 ------ holdCount=0 

[1636479870740] Consumer Consumer-2 waiting before while, with tail 21, head 11 ------ holdCount=0 

[1636479870740] Consumer Consumer-2 BEFORE lock!

with tail 21, head 11 ------ holdCount=0 

[1636479870740] Consumer Consumer-2 has LOCKED!

with tail 21, head 11 ------ holdCount=1 

[1636479870740] Consumer Consumer-2 EXTRACTED item 2 with tail 21, head 12 ------ holdCount=1 

[1636479870740] Consumer Consumer-2 ABOUT to unlock!

with tail 21, head 12 ------ holdCount=1 

[1636479870740] Consumer Consumer-2 has UNLOCKED!

with tail 21, head 12 ------ holdCount=0 

Thread Consumer-2 finished.

.

.

[1636479870741] Consumer Consumer-10 has LOCKED!

with tail 21, head 12 ------ holdCount=1 

[1636479870741] Consumer Consumer-10 EXTRACTED item 3 with tail 21, head 13 ------ holdCount=1 

[1636479870741] Consumer Consumer-10 ABOUT to unlock!

with tail 21, head 13 ------ holdCount=1 

[1636479870741] Consumer Consumer-10 has UNLOCKED!

with tail 21, head 13 ------ holdCount=0 

[1636479870741] Consumer Consumer-10 waiting before while, with tail 21, head 13 ------ holdCount=0 

[1636479870741] Consumer Consumer-10 BEFORE lock!

with tail 21, head 13 ------ holdCount=0 

[1636479870741] Consumer Consumer-10 has LOCKED!

with tail 21, head 13 ------ holdCount=1 

[1636479870741] Consumer Consumer-10 EXTRACTED item 2 with tail 21, head 14 ------ holdCount=1 

[1636479870742] Consumer Consumer-10 ABOUT to unlock!

with tail 21, head 14 ------ holdCount=1 

[1636479870742] Consumer Consumer-10 has UNLOCKED!

with tail 21, head 14 ------ holdCount=0 

[1636479870742] Consumer Consumer-10 waiting before while, with tail 21, head 14 ------ holdCount=0 

[1636479870742] Consumer Consumer-10 BEFORE lock!

with tail 21, head 14 ------ holdCount=0 

[1636479870742] Consumer Consumer-10 has LOCKED!

with tail 21, head 14 ------ holdCount=1 

[1636479870748] Consumer Consumer-10 EXTRACTED item 1 with tail 21, head 15 ------ holdCount=1 

[1636479870748] Consumer Consumer-10 ABOUT to unlock!

with tail 21, head 15 ------ holdCount=1 

[1636479870748] Consumer Consumer-10 has UNLOCKED!

with tail 21, head 15 ------ holdCount=0 

Thread Consumer-10 finished.

.

.

[1636479870749] Producer Producer-10 is has LOCKED -> tail 21, head 15, try to push x=1 ------ holdCount=1 

[1636479870749] Producer Producer-10 has ENQUEUED VALUE -> tail 22, head 15, try to push x=1 ------ holdCount=1 

[1636479870749] Producer Producer-10 is ABOUT to unlock -> tail 22, head 15, try to push x=1 ------ holdCount=1 

[1636479870749] Producer Producer-10 has UNLOCKED -> tail 22, head 15, try to push x=1 ------ holdCount=0 

Thread Producer-10 ended : Enqueue(1) of queue: [2, 3, 3, 1, 2, 3, 1]

Thread Producer-10 started: Enqueue(2) of queue: [2, 3, 3, 1, 2, 3, 1]

[1636479870749] Producer Producer-10 waiting before while, with tail 22, head 15, try to push x=2 

[1636479870750] Producer Producer-10 ABOUT to locks -> tail 22, head 15, try to push x=2 ------ holdCount=0 

[1636479870750] Producer Producer-10 is has LOCKED -> tail 22, head 15, try to push x=2 ------ holdCount=1 

[1636479870750] Producer Producer-10 has ENQUEUED VALUE -> tail 23, head 15, try to push x=2 ------ holdCount=1 

[1636479870750] Producer Producer-10 is ABOUT to unlock -> tail 23, head 15, try to push x=2 ------ holdCount=1 

[1636479870750] Producer Producer-10 has UNLOCKED -> tail 23, head 15, try to push x=2 ------ holdCount=0 

Thread Producer-10 ended : Enqueue(2) of queue: [2, 3, 3, 1, 2, 3, 1, 2]

Thread Producer-10 started: Enqueue(3) of queue: [2, 3, 3, 1, 2, 3, 1, 2]

[1636479870751] Producer Producer-10 waiting before while, with tail 23, head 15, try to push x=3 

[1636479870751] Producer Producer-10 ABOUT to locks -> tail 23, head 15, try to push x=3 ------ holdCount=0 

[1636479870751] Producer Producer-10 is has LOCKED -> tail 23, head 15, try to push x=3 ------ holdCount=1 

[1636479870751] Producer Producer-10 has ENQUEUED VALUE -> tail 24, head 15, try to push x=3 ------ holdCount=1 

[1636479870752] Producer Producer-10 is ABOUT to unlock -> tail 24, head 15, try to push x=3 ------ holdCount=1 

[1636479870755] Producer Producer-10 has UNLOCKED -> tail 24, head 15, try to push x=3 ------ holdCount=0 

Thread Producer-10 ended : Enqueue(3) of queue: [2, 3, 3, 1, 2, 3, 1, 2, 3]

Thread Producer-10 finished.

.

.

[1636479870756] Producer Producer-9 is has LOCKED -> tail 24, head 15, try to push x=1 ------ holdCount=1 

[1636479870757] Producer Producer-9 has ENQUEUED VALUE -> tail 25, head 15, try to push x=1 ------ holdCount=1 

[1636479870758] Producer Producer-9 is ABOUT to unlock -> tail 25, head 15, try to push x=1 ------ holdCount=1 

[1636479870758] Producer Producer-9 has UNLOCKED -> tail 25, head 15, try to push x=1 ------ holdCount=0 

Thread Producer-9 ended : Enqueue(1) of queue: [2, 3, 3, 1, 2, 3, 1, 2, 3, 1]

Thread Producer-9 started: Enqueue(2) of queue: [2, 3, 3, 1, 2, 3, 1, 2, 3, 1]

[1636479870761] Producer Producer-9 waiting before while, with tail 25, head 15, try to push x=2 

[1636479870762] Producer Producer-9 ABOUT to locks -> tail 25, head 15, try to push x=2 ------ holdCount=0 

[1636479870763] Producer Producer-9 is has LOCKED -> tail 25, head 15, try to push x=2 ------ holdCount=1 

[1636479870764] Producer Producer-9 has ENQUEUED VALUE -> tail 26, head 15, try to push x=2 ------ holdCount=1 

[1636479870764] Producer Producer-9 is ABOUT to unlock -> tail 26, head 15, try to push x=2 ------ holdCount=1 

[1636479870765] Producer Producer-9 has UNLOCKED -> tail 26, head 15, try to push x=2 ------ holdCount=0 

Thread Producer-9 ended : Enqueue(2) of queue: [2, 3, 3, 1, 2, 3, 1, 2, 3, 1, 2]

Thread Producer-9 started: Enqueue(3) of queue: [2, 3, 3, 1, 2, 3, 1, 2, 3, 1, 2]

[1636479870767] Producer Producer-9 waiting before while, with tail 26, head 15, try to push x=3 

[1636479870767] Producer Producer-9 ABOUT to locks -> tail 26, head 15, try to push x=3 ------ holdCount=0 

[1636479870768] Producer Producer-9 is has LOCKED -> tail 26, head 15, try to push x=3 ------ holdCount=1 

[1636479870769] Producer Producer-9 has ENQUEUED VALUE -> tail 27, head 15, try to push x=3 ------ holdCount=1 

[1636479870769] Producer Producer-9 is ABOUT to unlock -> tail 27, head 15, try to push x=3 ------ holdCount=1 

[1636479870770] Producer Producer-9 has UNLOCKED -> tail 27, head 15, try to push x=3 ------ holdCount=0 

Thread Producer-9 ended : Enqueue(3) of queue: [2, 3, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3]

Thread Producer-9 finished.

.

.

[1636479870775] Consumer Consumer-9 has LOCKED!

with tail 27, head 15 ------ holdCount=1 

[1636479870776] Consumer Consumer-9 EXTRACTED item 2 with tail 27, head 16 ------ holdCount=1 

[1636479870776] Consumer Consumer-9 ABOUT to unlock!

with tail 27, head 16 ------ holdCount=1 

[1636479870778] Consumer Consumer-9 has UNLOCKED!

with tail 27, head 16 ------ holdCount=0 

[1636479870779] Consumer Consumer-9 waiting before while, with tail 27, head 16 ------ holdCount=0 

[1636479870780] Consumer Consumer-9 BEFORE lock!

with tail 27, head 16 ------ holdCount=0 

[1636479870780] Consumer Consumer-9 has LOCKED!

with tail 27, head 16 ------ holdCount=1 

[1636479870781] Consumer Consumer-9 EXTRACTED item 3 with tail 27, head 17 ------ holdCount=1 

[1636479870782] Consumer Consumer-9 ABOUT to unlock!

with tail 27, head 17 ------ holdCount=1 

[1636479870783] Consumer Consumer-9 has UNLOCKED!

with tail 27, head 17 ------ holdCount=0 

[1636479870783] Consumer Consumer-9 waiting before while, with tail 27, head 17 ------ holdCount=0 

[1636479870784] Consumer Consumer-9 BEFORE lock!

with tail 27, head 17 ------ holdCount=0 

[1636479870785] Consumer Consumer-9 has LOCKED!

with tail 27, head 17 ------ holdCount=1 

[1636479870785] Consumer Consumer-9 EXTRACTED item 3 with tail 27, head 18 ------ holdCount=1 

[1636479870786] Consumer Consumer-9 ABOUT to unlock!

with tail 27, head 18 ------ holdCount=1 

[1636479870787] Consumer Consumer-9 has UNLOCKED!

with tail 27, head 18 ------ holdCount=0 

Thread Consumer-9 finished.

.

.

[1636479870788] Consumer Consumer-8 has LOCKED!

with tail 27, head 18 ------ holdCount=1 

[1636479870789] Consumer Consumer-8 EXTRACTED item 1 with tail 27, head 19 ------ holdCount=1 

[1636479870790] Consumer Consumer-8 ABOUT to unlock!

with tail 27, head 19 ------ holdCount=1 

[1636479870791] Consumer Consumer-8 has UNLOCKED!

with tail 27, head 19 ------ holdCount=0 

[1636479870792] Consumer Consumer-8 waiting before while, with tail 27, head 19 ------ holdCount=0 

[1636479870792] Consumer Consumer-8 BEFORE lock!

with tail 27, head 19 ------ holdCount=0 

[1636479870793] Consumer Consumer-8 has LOCKED!

with tail 27, head 19 ------ holdCount=1 

[1636479870794] Consumer Consumer-8 EXTRACTED item 2 with tail 27, head 20 ------ holdCount=1 

[1636479870795] Consumer Consumer-8 ABOUT to unlock!

with tail 27, head 20 ------ holdCount=1 

[1636479870795] Consumer Consumer-8 has UNLOCKED!

with tail 27, head 20 ------ holdCount=0 

[1636479870796] Consumer Consumer-8 waiting before while, with tail 27, head 20 ------ holdCount=0 

[1636479870797] Consumer Consumer-8 BEFORE lock!

with tail 27, head 20 ------ holdCount=0 

[1636479870797] Consumer Consumer-8 has LOCKED!

with tail 27, head 20 ------ holdCount=1 

[1636479870802] Consumer Consumer-8 EXTRACTED item 3 with tail 27, head 21 ------ holdCount=1 

[1636479870803] Consumer Consumer-8 ABOUT to unlock!

with tail 27, head 21 ------ holdCount=1 

[1636479870804] Consumer Consumer-8 has UNLOCKED!

with tail 27, head 21 ------ holdCount=0 

Thread Consumer-8 finished.

.

.

[1636479870805] Consumer Consumer-7 has LOCKED!

with tail 27, head 21 ------ holdCount=1 

[1636479870805] Consumer Consumer-7 EXTRACTED item 1 with tail 27, head 22 ------ holdCount=1 

[1636479870806] Consumer Consumer-7 ABOUT to unlock!

with tail 27, head 22 ------ holdCount=1 

[1636479870807] Consumer Consumer-7 has UNLOCKED!

with tail 27, head 22 ------ holdCount=0 

[1636479870807] Consumer Consumer-7 waiting before while, with tail 27, head 22 ------ holdCount=0 

[1636479870808] Consumer Consumer-7 BEFORE lock!

with tail 27, head 22 ------ holdCount=0 

[1636479870809] Consumer Consumer-7 has LOCKED!

with tail 27, head 22 ------ holdCount=1 

[1636479870809] Consumer Consumer-7 EXTRACTED item 2 with tail 27, head 23 ------ holdCount=1 

[1636479870810] Consumer Consumer-7 ABOUT to unlock!

with tail 27, head 23 ------ holdCount=1 

[1636479870811] Consumer Consumer-7 has UNLOCKED!

with tail 27, head 23 ------ holdCount=0 

[1636479870811] Consumer Consumer-7 waiting before while, with tail 27, head 23 ------ holdCount=0 

[1636479870812] Consumer Consumer-7 BEFORE lock!

with tail 27, head 23 ------ holdCount=0 

[1636479870813] Consumer Consumer-7 has LOCKED!

with tail 27, head 23 ------ holdCount=1 

[1636479870813] Consumer Consumer-7 EXTRACTED item 3 with tail 27, head 24 ------ holdCount=1 

[1636479870814] Consumer Consumer-7 ABOUT to unlock!

with tail 27, head 24 ------ holdCount=1 

[1636479870815] Consumer Consumer-7 has UNLOCKED!

with tail 27, head 24 ------ holdCount=0 

Thread Consumer-7 finished.

.

.

[1636479870817] Producer Producer-8 is has LOCKED -> tail 27, head 24, try to push x=1 ------ holdCount=1 

[1636479870817] Producer Producer-8 has ENQUEUED VALUE -> tail 28, head 24, try to push x=1 ------ holdCount=1 

[1636479870818] Producer Producer-8 is ABOUT to unlock -> tail 28, head 24, try to push x=1 ------ holdCount=1 

[1636479870819] Producer Producer-8 has UNLOCKED -> tail 28, head 24, try to push x=1 ------ holdCount=0 

Thread Producer-8 ended : Enqueue(1) of queue: [1, 2, 3, 1]

Thread Producer-8 started: Enqueue(2) of queue: [1, 2, 3, 1]

[1636479870820] Producer Producer-8 waiting before while, with tail 28, head 24, try to push x=2 

[1636479870821] Producer Producer-8 ABOUT to locks -> tail 28, head 24, try to push x=2 ------ holdCount=0 

[1636479870822] Producer Producer-8 is has LOCKED -> tail 28, head 24, try to push x=2 ------ holdCount=1 

[1636479870822] Producer Producer-8 has ENQUEUED VALUE -> tail 29, head 24, try to push x=2 ------ holdCount=1 

[1636479870823] Producer Producer-8 is ABOUT to unlock -> tail 29, head 24, try to push x=2 ------ holdCount=1 

[1636479870824] Producer Producer-8 has UNLOCKED -> tail 29, head 24, try to push x=2 ------ holdCount=0 

Thread Producer-8 ended : Enqueue(2) of queue: [1, 2, 3, 1, 2]

Thread Producer-8 started: Enqueue(3) of queue: [1, 2, 3, 1, 2]

[1636479870826] Producer Producer-8 waiting before while, with tail 29, head 24, try to push x=3 

[1636479870827] Producer Producer-8 ABOUT to locks -> tail 29, head 24, try to push x=3 ------ holdCount=0 

[1636479870828] Producer Producer-8 is has LOCKED -> tail 29, head 24, try to push x=3 ------ holdCount=1 

[1636479870828] Producer Producer-8 has ENQUEUED VALUE -> tail 30, head 24, try to push x=3 ------ holdCount=1 

[1636479870829] Producer Producer-8 is ABOUT to unlock -> tail 30, head 24, try to push x=3 ------ holdCount=1 

[1636479870854] Producer Producer-8 has UNLOCKED -> tail 30, head 24, try to push x=3 ------ holdCount=0 

Thread Producer-8 ended : Enqueue(3) of queue: [1, 2, 3, 1, 2, 3]

Thread Producer-8 finished.

.

.

[1636479870865] Consumer Consumer-1 has LOCKED!

with tail 30, head 24 ------ holdCount=1 

[1636479870866] Consumer Consumer-1 EXTRACTED item 1 with tail 30, head 25 ------ holdCount=1 

[1636479870867] Consumer Consumer-1 ABOUT to unlock!

with tail 30, head 25 ------ holdCount=1 

[1636479870868] Consumer Consumer-1 has UNLOCKED!

with tail 30, head 25 ------ holdCount=0 

[1636479870868] Consumer Consumer-1 waiting before while, with tail 30, head 25 ------ holdCount=0 

[1636479870868] Consumer Consumer-1 BEFORE lock!

with tail 30, head 25 ------ holdCount=0 

[1636479870868] Consumer Consumer-1 has LOCKED!

with tail 30, head 25 ------ holdCount=1 

[1636479870868] Consumer Consumer-1 EXTRACTED item 2 with tail 30, head 26 ------ holdCount=1 

[1636479870868] Consumer Consumer-1 ABOUT to unlock!

with tail 30, head 26 ------ holdCount=1 

[1636479870869] Consumer Consumer-1 has UNLOCKED!

with tail 30, head 26 ------ holdCount=0 

[1636479870869] Consumer Consumer-1 waiting before while, with tail 30, head 26 ------ holdCount=0 

[1636479870869] Consumer Consumer-1 BEFORE lock!

with tail 30, head 26 ------ holdCount=0 

[1636479870869] Consumer Consumer-1 has LOCKED!

with tail 30, head 26 ------ holdCount=1 

[1636479870869] Consumer Consumer-1 EXTRACTED item 3 with tail 30, head 27 ------ holdCount=1 

[1636479870869] Consumer Consumer-1 ABOUT to unlock!

with tail 30, head 27 ------ holdCount=1 

[1636479870869] Consumer Consumer-1 has UNLOCKED!

with tail 30, head 27 ------ holdCount=0 

Thread Consumer-1 finished.

.

.

[1636479870870] Consumer Consumer-6 has LOCKED!

with tail 30, head 27 ------ holdCount=1 

[1636479870870] Consumer Consumer-6 EXTRACTED item 1 with tail 30, head 28 ------ holdCount=1 

[1636479870870] Consumer Consumer-6 ABOUT to unlock!

with tail 30, head 28 ------ holdCount=1 

[1636479870870] Consumer Consumer-6 has UNLOCKED!

with tail 30, head 28 ------ holdCount=0 

[1636479870871] Consumer Consumer-6 waiting before while, with tail 30, head 28 ------ holdCount=0 

[1636479870871] Consumer Consumer-6 BEFORE lock!

with tail 30, head 28 ------ holdCount=0 

[1636479870871] Consumer Consumer-6 has LOCKED!

with tail 30, head 28 ------ holdCount=1 

[1636479870871] Consumer Consumer-6 EXTRACTED item 2 with tail 30, head 29 ------ holdCount=1 

[1636479870871] Consumer Consumer-6 ABOUT to unlock!

with tail 30, head 29 ------ holdCount=1 

[1636479870871] Consumer Consumer-6 has UNLOCKED!

with tail 30, head 29 ------ holdCount=0 

Thread Consumer-6 finished.

.

.

[1636479870872] Consumer Consumer-3 has LOCKED!

with tail 30, head 29 ------ holdCount=1 

[1636479870872] Consumer Consumer-3 EXTRACTED item 3 with tail 30, head 30 ------ holdCount=1 

[1636479870872] Consumer Consumer-3 ABOUT to unlock!

with tail 30, head 30 ------ holdCount=1 

[1636479870872] Consumer Consumer-3 has UNLOCKED!

with tail 30, head 30 ------ holdCount=0 

Thread Consumer-3 finished.

.

.

Process finished with exit code 0



  </details>

### Exercitiu 2b

Alogritmul Bakery este definit prin urmatorul pseudocod:
```java
1   class Bakery implements Lock {
2      boolean[] flag;
3      Label[] label;
4   
5      public Bakery (int n) {
6       	flag  = new boolean[n];
7       	label = new Label[n];
8       	for (int i = 0; i < n; i++) { 
9          		flag[i] = false; label[i] = 0;
10      	}
11     }
12  
13     public void lock() {  
14    	flag[i]  = true;  
15    	label[i] = max(label[0], ... ,label[n-1])+1;
16    	while (exists k!=i with flag[k]==true && (label[i],i) > (label[k],k)) {};
17     }
18  
19     public void unlock() {  
20     	flag[i] = false;
21     }
22  }
```

In algoritmul Bakery nu este suficienta doar compararea labelurilor intrucat, datorita limitarilor arhitecturii actuale, 
la asignarea labelurilor poate aparea un race condition si prin urmare, mai mult de un thread sa primeasca acelasi label.
Aceasta problema este rezolvata prin adaugarea unui al doilea criteriu de sortare (de obicei numarul threadului), si din
acest motiv este nevoie sa se compare tupla(label, identificator_thread) si nu doar labelul.

Presupunem ca facem urmatoarea modificare a pseudocodului la linia 16, pentru a compara doar labelurile:
```java
while (exists k!=i with flag[k]==true && (label[i],i) > (label[k],k)) {};
```
devine
```java
while (exists k!=i with flag[k]==true && label[i] > label[k]) {};
```

Cu acest nou pseudocod, presupunem urmatorul caz:

Fie **A**, **B** doua thread-uri si resursa partajata initializata cu:
```java
label = [A:0, B:0]
flag = [A: false, b: false]
```
Executia thread-urilor va decurge in urmatorul fel:
```java
write_A(flag[A], true) //linia 14
write_B(flag[B], true) //linia 14
        
read_B(label[A]) -> 0 //linia 15
read_A(label[B]) -> 0 //linia 15
        
write_B(label[B], 1) // linia 15
write_A(label[A], 1) // linia 15

evaluate_B(exists k!=i with flag[k]==true && label[i] > label[k]) -> false // linia 16
evaluate_A(exists k!=i with flag[k]==true && label[i] > label[k]) -> false // linia 16
```

Cum atat B cat si A au evaluat expresia de la linia 16 ca fiind false, vor intra amandoua in zona critica simultan,
lucru ce va rezulta intr-un data racing.

Prin acest exemplu, am demonstrat ca nu este de ajuns doar compararea labelurilor, putand sa se ajunga intr-un data race. Astfel, am demonstrat si
ca este necesara si compararea unui alt criteriu unic (identificatorul de thread).

### Exercitiu 2c

```java
// lock inainte de try:

someLock.lock();
try {
   .....
}
finally {
   someLock.unlock();
}

```

Este preferat sa facem lock-ul inainte de blocul try, pentru ca daca apelul metodei lock() arunca vreo exceptie, acea exceptie este
prinsa de catre managerul global de exceptii si se va iesi din functie fara a se mai executa blocurile **try** si **finally**.
```java
// lock in cadrul try:
try {
   someLock.lock();
   .....
}
finally {
   someLock.unlock();
}

```

Daca metoda lock se afla in interiorul blocului **try**, si exista vroe problema, in momentul cand aceasta arunca vreo exceptie, exceptia este prinsa si se
ajunge cu executia in bloc-ul **finally** unde se incearca apelarea metodei unlock. 

Metoda unlock va incerca sa deblocheze un lock nedetinut (*lock-ul a esuat si a aruncat o exceptie*), moment in care va esua 
si va arunca exceptia **IllegalMonitorStateException**, motiv pentru care prima varianta de cod este preferata.


### Exercitiu 3
[![N|Solid](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/uml.png)](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/uml.png)

#### Structura
Codul sursa este impartit in 

- interfete (actorii: resursa partajata, oala - IPot, consumatorii - ISavage, cel care reincarca resursa - ICook) si 
- implementarile care corespund exercitiului rezolvat (3.a sau 3.b). Pentru a alege ce subpunct e executat exista fisierul de configurare "config.txt", unde se adauga "a" sau "b" drept continutul integral al fisierului
         
#### Ouput
Rezultatul executiei vor fi o serie de linii printate de thread-uri care urmaresc evolutia in mod detaliat a programului.

#### A.)
````
The_Cook started...
  The_Cook waits for further notification...

  Savage-0 started...
  Savage-0 has notified the Cook for a new refill 
  Savage-0 waits for the Cook to respond to the request 
  The_Cook stopped waiting...
  The_Cook refilled...
  The_Cook waits for further notification...
  Savage-0 has taken a ration from the pot, which is now left with only 6 
  Savage-0 finished...

  Savage-1 started...
  Savage-1 has taken a ration from the pot, which is now left with only 5 
  Savage-1 finished...

  Savage-2 started...
  Savage-2 has taken a ration from the pot, which is now left with only 4 
  Savage-2 finished...

  Savage-3 started...
  Savage-3 has taken a ration from the pot, which is now left with only 3 
  Savage-3 finished...

  Savage-4 started...
  Savage-4 has taken a ration from the pot, which is now left with only 2 
  Savage-4 finished...

  Savage-5 started...
  Savage-5 has taken a ration from the pot, which is now left with only 1 
  Savage-5 finished...
  
  [.....]
  
  Savage-46 started...
  Savage-46 has taken a ration from the pot, which is now left with only 1 
  Savage-46 finished...

  Savage-45 started...
  Savage-45 has taken a ration from the pot, which is now left with only 0 
  Savage-45 finished...

  Cook is stopping because no savage is hungry anymore...

  Process finished with exit code 0
````
#### B.)

Verbose with emphasis at the consistent order of threads and the fairness
[![N|Solid](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/Fig3.PNG)](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/Fig3.PNG)

#### Mentiuni
Spre deosebire de punctul A.), care se opreste natural cand toate thread-urile consumatoare sunt satisfacute, exercitiul B.) are un timp de rulare pana la care se poate opri astfel incat programul sa nu mearga pana la nesfarsit (30 de secunde).
