# Scala Project estimating Pi with Cats

Based on the [cats-seed.g8][cats-seed] template by [Underscore][underscore].

Copyright Damon Snyder. Licensed [CC0 1.0][license].

## Getting Started

From the SBT prompt you can run the code in `Main.scala`:

```bash
> run
[info] Compiling 1 Scala source to <DIRECTORY>...
[info] Running picats.Main
Estimated 3.064 for Pi after 1000 iterations.
[success] Total time: 5 s, completed
```

You can also run it from the command line:
```bash
> sbt -warn "run 100000"
Estimated 3.14916 for Pi after 100000 iterations.
[success] Total time: 2 s, completed
```