# My Labs
## _Tehnici de programare multiprocesor 2021_
[![N|Solid](https://plati-taxe.uaic.ro/img/logo-retina1.png)](https://www.info.uaic.ro/)

Croitoru Razvan 3A2
Diac P. Gabriel 3A2



## Tema 1
### Exercitiu 3
[![N|Solid](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/uml.png)](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/uml.png)

#### Structura
Codul sursa este impartit in 

- interfete (actorii: resursa partajata, oala - IPot, consumatorii - ISavage, cel care reincarca resursa - ICook) si 
- implementarile care corespund exercitiului rezolvat (3.a sau 3.b). Pentru a alege ce subpunct e executat exista fisierul de configurare "config.txt", unde se adauga "a" sau "b" drept continutul integral al fisierului
         
#### Ouput
Rezultatul executiei vor fi o serie de linii printate de thread-uri care urmaresc evolutia in mod detaliat a programului.

A.)
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
B.) Verbose with emphasis at the consistent order of threads and the fairness
[![N|Solid](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/Fig3.PNG)](https://github.com/gabidiac11/multiprocessor-programming-techniques-java-homework/blob/main/Homework1_ex3/Fig3.PNG)

#### Mentiuni
Spre deosebire de punctul A.), care se opreste natural cand toate thread-urile consumatoare sunt satisfacute, exercitiul B.) are un timp de rulare pana la care se poate opri astfel incat programul sa nu mearga pana la nesfarsit (30 de secunde).
