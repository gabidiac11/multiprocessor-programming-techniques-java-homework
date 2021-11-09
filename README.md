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

Daca metoda lock se afla in interiorul blocului **try**, in momentul cand aceasta arunca vreo exceptie, exceptia este prinsa si se
ajunge cu executia in bloc-ul **finally** unde se incearca apelarea metodei unlock. 

Metoda unlock va incerca sa deblocheze un lock neblocat (*lock-ul a esuat si a aruncat o exceptie*), moment in care va esua 
si va arunca o exceptie pentru toate tipurile de lock mai putin, ReentrantLock.

Astfel, prima varianta este cea preferata.

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
