# Libpython-clj clj-template

* https://github.com/seancorfield/clj-new


## Usage

#### libpython-clj projects can now be created quickly in 2 ways from the latest stable template:


-   **without** clj-new installed in user deps.edn

```bash 

# example
clj -Sdeps '{:deps {seancorfield/clj-new {:mvn/version "0.8.6"}}}' \
  -m clj-new.create \
  https://github.com/clj-python/clj-template@06be22304f17d0be28652575692d7b599e8a51f7 \
  appcompany.funapp
```

-    **with** clj-new [installed](https://github.com/seancorfield/clj-new) in user deps.edn (recommended)

```bash 
# example
clj -A:new \
  https://github.com/clj-python/clj-template@06be22304f17d0be28652575692d7b599e8a51f7 \
  appcompany.funapp
```

   **NOTE**: this assumes you have `clj-new` configured in you `~/.clojure/deps.edn`
   profile. If you do not, you can use the following:
   
   

For help please visit our [help-wanted](https://clojurians.zulipchat.com/#narrow/stream/215609-libpython-clj-dev/topic/help-wanted) topic.

For configuration option requests, please file a Github issue or visit our [feature requests]( https://clojurians.zulipchat.com/#narrow/stream/215609-libpython-clj-dev/topic/feature-requests) topic.  
