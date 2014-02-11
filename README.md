# Code Sounds

Like code smells only louder. 

Exploring how we can use sound to reveal the shape of a code base.

## What do Function sound like?

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

## License

Copyright © 2014 Joseph Wilk

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
