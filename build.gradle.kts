plugins {
    id("nl.littlerobots.version-catalog-update") apply(true)
}

tasks.create<Delete>("clean") {
    delete {
        rootProject.buildDir
    }
}
