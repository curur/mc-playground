dependencies {

    implementation(project(":mcp-api"))
    implementation(project(":mcp-core"))
}


tasks.processResources {
    filesMatching("plugin.yml") {
        expand(project.properties)
    }
}