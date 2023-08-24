# Play [Gossip Glomers](https://fly.io/dist-sys/) with Kotlin  

in this directory:
```
./gradlew :echo:clean :echo:shadowJar

```

in [maelstrom](https://github.com/jepsen-io/maelstrom) directory:
```
./maelstrom test -w echo --bin $HOME/code/gossip-glomers/echo/run.sh --node-count 3 --time-limit 5
```
