# Code Sounds

Like code smells only louder. 

Exploring how we can use sound to reveal the shape of a code base.

## Describing code

```clojure
(defn noisy [a b c d e f g h i j k]
  (+ a b c d e g h i j k))
```

```clojure
(defn disorderly [a b]
  (let [x 1]
    (let [y 2]
      (let [z 3]
        (let [a 4] 
          (let [b 5]))))))
```

```clojure
(defn verbose [this-is-a-variable-that-i-think-might-be-a-bit-long]
  this-is-a-variable-that-i-think-might-be-a-bit-long)
```

```clojure
(defn inconsistent [what-shall-we-do]
  (let [what_shall_we_do 10]
    (or what-shall-we-do what_shall_we_dp)))
```


## License

(The MIT License)

Copyright (c) 2014 Joseph Wilk

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
