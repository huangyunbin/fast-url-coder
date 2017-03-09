# fast-url-coder


##我自己写的比ziesemer好的原因有两个：

### 1 ziesemer的CharBuffer扩容的时候，


    public CharBuffer put(CharBuffer src) {
        for (int i = 0; i < n; i++)
            put(src.get());           
   

  是构建一个2倍大小的新的，然后把之前的遍历所有元素put进去.
  
  我的FastCharArrayWriter扩容的时候
  
    buf = Arrays.copyOf(buf, Math.max(buf.length << 1, newcount));

 底层用的是System.arraycopy，这个是批量复制
    
    
### 2 我在char[]转为string的时候，是要复制一次的，因为string要保持不可变性  
  
    public String(char value[], int offset, int count) {
        this.value = Arrays.copyOfRange(value, offset, offset+count);
    }
    
    
   我这里用到了**SharedSecrets.getJavaLangAccess().newStringUnsafe**，这个可以不复制char[]，直接引用char[]。
