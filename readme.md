# Algorithm-Java
General algorighm implements in java

## Implements
### IDCardChecker
Reference to: [ChineseIdCardValidate.pdf](./docs/ChineseIdCardValidate.pdf)

### SipHash
Reference to: [siphash.pdf](./docs/siphash.pdf)

### SHA256
Reference to: [SHA256.pdf](./docs/SHA256.pdf)

### MurmurHash2
Reference to: [MurmurHash2.c](https://github.com/RedisBloom/RedisBloom/blob/master/contrib/MurmurHash2.c)

### BloomFilter
Reference to: [BloomFilter.pdf](./docs/BloomFilter.pdf)

### Bits
| method | remark |
|--------|--------|
| inverse | `~` |
| and | `&` |
| or | `|` |
| xor | `^` |
| lShift | `<<` |
| rShift | `>>` |
| rrShift | `>>>` |

### LinearCongruentialRandom
Reference to: [LinearCongruence.pdf](./docs/LinearCongruence.pdf)

### MersenneTwisterRandom
Reference to: [MersenneTwister.pdf](./docs/MersenneTwister.pdf)

## Installation
```bash
<dependency>
  <groupId>com.github.myibu</groupId>
  <artifactId>algorithm-java</artifactId>
  <version>1.0.0a</version>
</dependency>
```

## Examples
```java
SHA256 sha256 = new SHA256();
byte[] bs = sha256.encode("abc".getBytes("utf-8"));
```
