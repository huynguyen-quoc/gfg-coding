Provide a list of violations in the current implementation

1. Use field injection @Autowired for dependency injection
2. Do not have any test case for the current implementation
3. No documentation for the api or source code
4. No profiling for production, development, staging environment
5. Redundant getter setter while using with DI @Autowire
6. Use h2 database that can only be used for testing
7. No migration tool
8. API is not follow REST API specification( 
   URL is not a resources,
   HttpMethod is POST for all API,
   HttpStatus is 200 OK for all cases)
9. No Dockerfile to use service with docker
10. Call repository directly, and the business logic is on controller
11. A redundant constructor which called super without inheritance
```
 public Product() {
        super();
 }
```
12. Use JPARepository instanceof CRUDRepository
13. Redundant throws exception
14. using optional object wrong
```
if (existingProduct == null)
```
15. No pre validation for request body of create and update
16. Redundant set value when update product
17. No implement Serializable object for DTO and Entity
18. Too much boilerplate from java coding
19. No logging to help maintain