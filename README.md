# Libpython-clj clj-template

* https://github.com/seancorfield/clj-new


## Usage

#### libpython-clj projects can now be created quickly in 2 ways from the latest stable template:

-    **with clj-new installed in user deps.edn**

```bash 
clj -A:new clj-python <mydomain.myapp>

;; example
clj -A:new clj-python appcompany.funapp
```

   **NOTE**: this assumes you have `clj-new` configured in you `~/.clojure/deps.edn`
   profile. If you do not, you can use the following:
   
-   **without clj-new installed in user deps.edn**
   
   syntax:
```bash 
clj -Sdeps '{:deps {seancorfield/clj-new {:mvn/version "0.8.6"}}}' \
-m clj-new.create \
clj-python <mydomain.myapp>

;; example
clj -Sdeps '{:deps {seancorfield/clj-new {:mvn/version "0.8.6"}}}' \
-m clj-new.create \
clj-python appcompany.funapp
```
