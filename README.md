# Reactive-functional-java
A general bucket for some functional/reactive code in java inspired by scala, using the benefits that java 8 gives us.

##Result
The ```Result``` object is a bit more than a simple tuple. It is designed to be used to explicitly signal to an 
API consumer, that a certain method can result in more than one effect. It can result with a ```SUCCESS``` or 
with an ```ERROR```.

The ```Result``` can be instantiated using two simple static methods - ```failWith(...)``` and ```succeedWith()```.

###Example
In a standard situation you would define a method as something like this:
```java
public class SomeClass {
  public SomeResult aMethod(...) {
    if (condition) {
      return new SomeResult(...)
    } else {
      throw new SomeRuntimeException(...)
    }
  }
}
```
By looking only at the signature of the method the consumer doesn't know immediately of all the effects that 
this method can result with. With ```Result``` you can explicitly define all the effects that this method has:
```java
public class SomeClass {
  public Result<SomeResult, SomeError> aMethod(...) {
    if (condition) {
      return Result.succeedWith(new SomeResult(...));
    } else {
      return Result.failWith(new SomeError(...));
    }
  }
}
```
The consumer can then define consumers of success and failure cases:
```java
SomeClass someClass = new SomeClass(...);
...
final Result<SomeResult, SomeError> result = someClass.aMethod(...);
result.onSuccess(success -> success.get().doSomething())
      .onFailure(error -> LOG.error("Failed to call aMethod with errror: {}"), error)
```
...or define some recovery cases:
```java
SomeClass someClass = new SomeClass(...);
...
final Result<SomeResult, SomeError> result = someClass.aMethod(...);
result.getOrRecoverWith(error -> new SomeDefaultResult(...));
```
