# Play [Gossip Glomers](https://fly.io/dist-sys/) with Kotlin  

in this directory:
```
./gradlew :echo:clean :echo:shadowJar
```

in [maelstrom](https://github.com/jepsen-io/maelstrom) directory:
```
./maelstrom test -w echo --bin $HOME/code/gossip-glomers/echo/run.sh --node-count 3 --time-limit 5
```

## Notes

"Client Timeout" means node implementation has some bugs.

All stderr logs are under:
`/path/to/maelstrom/store/echo/latest`

for example:
`$HOME/Downloads/maelstrom/store/echo/latest/node-logs/n0.log`