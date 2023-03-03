## **Clevertec Task 6 - Concurrency**

### **Task**

1. Create two classes:
   - The client has a list of requests (a class with an int field), which it sends to the server asynchronously,  
     and receives responses by adding them to another list.
   - The server receives requests from the client. Processes them (some simple computing function,  
     for example: resp.value = 100 - req.value). The function processing the request has a delay in the form  
     of a random int. The range is up to 2000 ms.
2. Test these two classes with a multithreading check.
3. Test the client-server interaction with a separate test (integration).
4. In the implementation use the classes of the java.util.concurrent package  
   (necessarily Lock, Callable, Executor, Future, the rest is optional).
