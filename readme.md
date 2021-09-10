# Algorithm-Java
General algorighm implements in java

## Implements
### IDCardChecker
Reference to: [ChineseIdCardValidate.pdf](./docs/ChineseIdCardValidate.pdf)

### SipHash
Reference to: [siphash.pdf](./docs/siphash.pdf)

### SHA256
Reference to: [SHA256.pdf](./docs/SHA256.pdf)

## Installation
```bash
<dependency>
  <groupId>com.github.myibu</groupId>
  <artifactId>algorithm-java</artifactId>
  <version>0.0.1</version>
</dependency>
```

## Examples
```java
SHA256 sha256 = new SHA256();
byte[] bs = sha256.encode("abc".getBytes("utf-8"));
```
