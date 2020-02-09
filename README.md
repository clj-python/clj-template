# Libpython-clj clj-template

* https://github.com/seancorfield/clj-new


## Usage


* libpython-clj projects can now be created quickly with:

```bash 

    clj -A:new https://github.com/clj-python/clj-template <project.name>
```
   **NOTE**: this assumes you have `clj-new` configured in you `~/.clojure/deps.edn`
   profile. If you do not, you can use the following:
   
```bash 
clj -Sdeps '{:deps {seancorfield/clj-new {:mvn/version "0.8.6"}}}' \
    -m clj-new.create \
	https://github.com/clj-python/clj-template@e24f817a3e804239540b666f8d39f5938a434543 \
	myname.myapp myname/myapp
```
