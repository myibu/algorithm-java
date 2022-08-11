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

### DFASensitiveWordFilter

### AhoCorasickSensitiveWordFilter

### LZ77Compressor
Reference to: [LZ77.pdf](./docs/LZ77.pdf)

### LZWCompressor
Reference to: [LZW.pdf](./docs/LZW.pdf)

### LZFCompressor
Reference to: 
[lzf_c.c](./docs/lzf_c.c)
[lzf_d.c](./docs/lzf_d.c)

### GolombEncoder
Reference to: [HoffmanAndGolombCoding.pdf](./docs/HoffmanAndGolombCoding.pdf)

## Installation
```bash
<dependency>
  <groupId>com.github.myibu</groupId>
  <artifactId>algorithm-java</artifactId>
  <version>1.0.3</version>
</dependency>
```

## Examples
```java
SHA256 sha256 = new SHA256();
byte[] bs = sha256.encode("abc".getBytes("utf-8"));
```
