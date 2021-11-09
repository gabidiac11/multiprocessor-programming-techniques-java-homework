# My Labs
## _Tehnici de programare multiprocesor 2021_
[![N|Solid](https://plati-taxe.uaic.ro/img/logo-retina1.png)](https://www.info.uaic.ro/)

Croitoru Razvan 3A2
Diac P. Gabriel 3A2



## Tema 1

### Continut
- [x] [Exercitiu 1](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/README.md#exercitiu-1)
- [ ] Exercitiu 2a
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
    Output-ul problemei de caching
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
