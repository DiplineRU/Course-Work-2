ru text
# Курсовая работа 2-го курса

> Привет!
На связи курсовая работа второго курса профессии Java-разработчик. 
На её выполнение у вас будет 1 неделя, в течение которой новых уроков не будет. 
Вы можете консультироваться с наставниками и обсуждать решение с одногруппниками. 
На платформе в качестве выполненного задания присылайте ссылку на проект в GitHub.
> 

В курсовой работе ко второму курсу вам необходимо реализовать приложение, которое будет генерировать вопросы к экзамену.

### Путь пользователя

1. Пользователь обращается к некому эндпоинту по адресу (”/exam/get/{amount}”)
2. Пользователь получает ответ в виде списка случайных вопросов-ответов, количество которых равно amount из прошлого шага
3. Пользователь должен иметь возможность добавить, получить и удалить вопросы из хранилища вопросов (”/exam/java/(add/remove/find)”)

### Реализация приложения

Для упрощения архитектурного понимания, вам будут даны подсказки по организации проекта.

1. Реализовать сущность Question с двумя полями: question и answer. Данная сущность будет использоваться в качестве хранителя данных по вопросу.
    - Архитектура
        
        ![Untitled](https://github.com/user-attachments/assets/082f7f70-a8d8-4017-a199-dc3526871f35)

2. Сделать интерфейс QuestionService, который будет содержать в себе все методы по работе с вопросами определенного предмета.
    - Архитектура
        
        ![diagram-17353247503607978677](https://github.com/user-attachments/assets/e2d04627-bfd1-4340-be7a-d2af5f7a62f2)

        
3. Реализовать сервис JavaQuestionService, который будет реализовывать QuestionService и хранить в себе список вопросов по Java, а также осуществлять всю работу с этим списком.
    
    Реализация метода getRandomQuestion осуществляется с помощью класса Random и его метода nextInt, который в качестве параметра принимает максимальное число, а затем возвращает вам результат в виде случайного числа от 0 до максимального числа из параметров (не включительно).
    
    - Архитектура
        
        
        ![Untitled](https://github.com/user-attachments/assets/4d3a6bdf-328a-4e57-97a3-b21b0ed38a3b)

4. Реализовать контроллер JavaQuestionController, который будет предоставлять возможность пользователю добавлять, просматривать и удалять вопросы по Java в соответствующем QuestionService.
    
    Контроллер должен иметь три метода: добавить, удалить и получить все вопросы.
    
    Эти методы должны висеть на следующих эндпоинтах:
    
    Добавить: “/exam/java/add?question=QuestionText&answer=QuestionAnswer”
    
    Удалить: “/exam/java/remove?question=QuestionText&answer=QuestionAnswer”
    
    Получить все вопросы: “/exam/java”
    
    - Архитектура
        
        ![Untitled](https://github.com/user-attachments/assets/c070d291-44d2-4af8-b20e-a7c6486ef48f)

        
5. Сделать интерфейс ExaminerService с одним методом getQuestions.
    
    Этот интерфейс должен содержать один метод, который вернет список вопросов.
    
    - Архитектура
        
        ![Untitled](https://github.com/user-attachments/assets/5d1b97f8-d73f-4359-b602-33f63698b52b)

        
6. Реализовать ExaminerServiceImpl, который является реализацией интерфейса из прошлого шага. Данный сервис должен внутри себя хранить поля типа QuestionService.
    
    Его задача: создать коллекцию и заполнить её с помощью вызова getRandomQuestion у QuestionService случайными вопросами. 
    
    Учтите:
    
    -  Вопросы должны быть уникальные, следовательно, для получения 5 вопросов может потребоваться более 5 вызовов метода getRandomQuestion сервиса вопросов.
    - Если запрошено большее количество вопросов, чем хранится в сервисе, нужно выкинуть исключение. Для этого, соответственно, нужно написать свое исключение со статусом BAD_REQUEST.
      - Архитектура
        
        ![Untitled](https://github.com/user-attachments/assets/0fc48bc6-8172-4fa6-82e2-df3098343ff7)

        
7. Реализовать контроллер ExamController с одним методом getQuestions(int amount).
    
    Контроллер должен обратиться к ExaminerService, получить от сервиса коллекцию вопросов и вернуть пользователю.
    
8. Покрыть юнит-тестами JavaQuestionService и ExaminerServiceImpl (потребуется мок).

- **Критерии оценки курсовой** (только базовый обязательный уровень):
    - Использованы все методы, указанные в архитектуре
    - В написании кода используется правильное форматирование
    - DI создан с использованием конструктора
    - Юнит-тестами покрыты JavaQuestionService и ExaminerServiceImpl
    - Метод getRandomQuestion не обязательно покрыт юнит-тестами
    - В коллекциях нет двух вопросов, у которых вопрос-ответ имеет одинаковое значение (поля q и a имеют одинаковое значение)
    - Будет плюсом: в коллекциях указаны только уникальные вопросы
    

### Повышенная сложность:

1. Реализовать ещё одну реализацию для QuestionService, а именно MathQuestionService.
    
    Данный сервис должен работать по аналогии с JavaQuestionService, но с математическими примерами.
    
2. Реализовать контроллер MathQuestionController, который позволяет добавлять, удалять и получать список математических вопросов. Для получения конкретной реализации интерфейса QuestionService может потребоваться аннотация @Qualifier.
3. Перенести функцию хранение вопросов из сервисов в отдельные сущности — репозитории. Для этого потребуется реализовать интерфейс QuestionRepository с методами add, remove и getAll. А затем написать две реализации для вопросов по Java и по математике.
    
    Эти сущности нужно заинжектить в соответствующие сервисы и в сервисах “дергать” репозиторий в случае необходимости добавления, удаления и получения вопросов.
    
    Допустимо также реализовать @PostConstruct метод init, который заполнит репозиторий данными сразу после его создания Spring. 
    
    - Архитектура
        
        ![Untitled](https://github.com/user-attachments/assets/77959e83-83d9-4291-b0e8-c6149d761ac2)

        
4. Доработать ExaminerService на получение случайного набора вопросов не только из JavaQuestionService, но и из MathQuestionService. Включать в запрос вопросы не только по джаве, но и по математике. Количество вопросов по каждой из тем выбирать случайно. Для получения конкретной реализации интерфейса QuestionService может потребоваться аннотация @Qualifier.
5. Доработать JavaQuestionController, так как вторая реализация QuestionService сломала корректный инжект по интерфейсу. Может потребоваться аннотация @Qualifier.
6. Покрыть юнит-тестами MathQuestionService (с моком), оба репозитория.
7. Переработать юнит-тесты для JavaQuestionService с учетом ввода репозитория (добавить мок).
8. Переработать юнит-тесты для ExaminerServiceImpl с учетом добавления второго сервиса вопросов.

### Mastermind:

1. Удалить MathQuestionRepository.
2. Теперь на попытки добавить, удалить и получить все вопросы по математике должно выбрасываться исключение со статусом 405 Method Not Allowed
3. Добавить в метод getRandomQuestion сервиса MathQuestionService генерацию вопросов по математике “на лету”. Это возможно с помощью уже упомянутого ранее класса Random.
4. Избавиться от полей для каждого сервиса вопросов в ExaminerServiceImpl. Собрать их в коллекцию. Переработать способ сборки коллекции вопросов с учетом этого изменения.

eng text

# Course work of the 2nd year

> Hello!
I am in touch with the course work of the second year of the Java developer profession. 
You will have 1 week to complete it, during which there will be no new lessons. 
You can consult with mentors and discuss the decision with your classmates. 
On the platform, send a link to the project to GitHub as a completed task.
> 

In the course work for the second year, you need to implement an application that will generate exam questions.

### User's path

1. The user accesses a certain endpoint at the address (”/exam/get/{amount}”)
2. The user receives an answer in the form of a list of random question-answers, the number of which is equal to the amount from the last step
3. The user should be able to add, receive and delete questions from the question repository (”/exam/java/(add/remove/find)”)

### Application implementation

To simplify the architectural understanding, you will be given hints on the organization of the project.

1. Implement the Question entity with two fields: question and answer. This entity will be used as a data keeper for the issue.
    - Architecture
        
        ![Untitled](https://github.com/user-attachments/assets/082f7f70-a8d8-4017-a199-dc3526871f35)

2. Make the QuestionService interface, which will contain all the methods for working with questions of a certain subject.
    - Architecture
        
        ![diagram-17353247503607978677](https://github.com/user-attachments/assets/e2d04627-bfd1-4340-be7a-d2af5f7a62f2)

        
3. Implement the JavaQuestionService service, which will implement the QuestionService and store a list of Java questions, as well as carry out all work with this list.
    
    The getRandomQuestion method is implemented using the Random class and its nextInt method, which takes the maximum number as a parameter, and then returns you the result as a random number from 0 to the maximum number of parameters (not inclusive).
    
    - Architecture
        
        
        ![Untitled](https://github.com/user-attachments/assets/4d3a6bdf-328a-4e57-97a3-b21b0ed38a3b)

4. Implement the JavaQuestionController controller, which will enable the user to add, view and delete Java questions in the corresponding QuestionService.
    
    The controller should have three methods: add, delete and get all the questions.
    
    These methods should hang on the following endpoints:
    
    Add: “/exam/java/add?question=QuestionText&answer=QuestionAnswer”
    
    Delete: “/exam/java/remove?question=QuestionText&answer=QuestionAnswer”
    
    Get all the questions: “/exam/java”
    
    - Architecture
        
        ![Untitled](https://github.com/user-attachments/assets/c070d291-44d2-4af8-b20e-a7c6486ef48f)

        
5. Make the ExaminerService interface with a single getQuestions method.
    
    This interface should contain one method that will return a list of questions.
    
    - Architecture
        
        ![Untitled](https://github.com/user-attachments/assets/5d1b97f8-d73f-4359-b602-33f63698b52b)

        
6. Implement ExaminerServiceImpl, which is the implementation of the interface from the last step. This service must store fields of the QuestionService type inside itself.
    
    Its task is to create a collection and fill it with random questions by calling getRandomQuestion from QuestionService. 
    
    Please note:
    
    - The questions must be unique, therefore, it may take more than 5 calls to the getRandomQuestion method of the question service to get 5 questions.
    - If more questions are requested than are stored in the service, you need to throw an exception. To do this, accordingly, you need to write your own exception with the status BAD_REQUEST.
      - Architecture
        
        ![Untitled](https://github.com/user-attachments/assets/0fc48bc6-8172-4fa6-82e2-df3098343ff7)

        
7. Implement the ExamController controller with a single getQuestions(int amount) method.
    
    The controller must contact ExaminerService, receive a collection of questions from the service and return them to the user.
    
8. Cover JavaQuestionService and ExaminerServiceImpl with unit tests (IOC will be required).

- **Course assessment criteria** (only the basic mandatory level):
- All methods specified in the architecture are used
    - The correct formatting is used in writing the code
    - DI is created using the constructor
- Unit tests are covered by JavaQuestionService and ExaminerServiceImpl
    - The getRandomQuestion method is not necessarily covered by unit tests
- There are no two questions in collections where the question-answer has the same value (fields q and a have the same value)
- It will be a plus: only unique questions are specified in collections
    

### Increased difficulty:

1. Implement another implementation for QuestionService, namely MathQuestionService.
    
    This service should work by analogy with JavaQuestionService, but with mathematical examples.
    
2. Implement the MathQuestionController controller, which allows you to add, delete and get a list of mathematical questions. To get a specific implementation of the QuestionService interface, an annotation @Qualifier may be required.
3. Transfer the function of storing questions from services to separate repository entities. To do this, you will need to implement the QuestionRepository interface with the add, remove and GetAll methods. And then write two implementations for Java and math questions.
    
    These entities need to be injected into the appropriate services and the repository should be “yanked” in the services if necessary to add, delete and receive questions.
    
    It is also acceptable to implement the @PostConstruct init method, which will fill the repository with data immediately after its creation by Spring. 
    
    - Architecture
        
        ![Untitled](https://github.com/user-attachments/assets/77959e83-83d9-4291-b0e8-c6149d761ac2)

        
4. Refine ExaminerService to get a random set of questions not only from JavaQuestionService, but also from MathQuestionService. Include questions in the query not only on java, but also on mathematics. The number of questions for each topic should be chosen randomly. To get a specific implementation of the QuestionService interface, an annotation @Qualifier may be required.
5. Finalize the JavaQuestionController, since the second QuestionService implementation broke the correct injection on the interface. The @Qualifier annotation may be required.
6. Cover both repositories with MathQuestionService unit tests (with mock).
7. Redesign the unit tests for JavaQuestionService, taking into account the repository input (add mock).
8. Redesign the unit tests for ExaminerServiceImpl, taking into account the addition of a second question service.

### Mastermind:

1. Delete MathQuestionRepository.
2. Now an exception with the status 405 Method Not Allowed should be thrown for attempts to add, delete and get all math questions
3. Add MathQuestionService's on-the-fly math question generation to the getRandomQuestion method. This is possible with the help of the previously mentioned Random class.
4. Get rid of the fields for each question service in ExaminerServiceImpl. Collect them into a collection. Redesign the way the collection of questions is assembled with this change in mind.
