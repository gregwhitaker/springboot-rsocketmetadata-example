# springboot-rsocketmetadata-example
An example of sending per-request metadata with [RSocket](http://rsocket.io) and Spring Boot.

## Building the Example
Run the following command to build the example:

    ./gradlew clean build

## Running the Example
Follow the steps below to run the example:

1. Run the following command to start the `hello-service`:

        ./gradlew :hello-service:bootRun
        
2. In a new terminal, run the following command to call the `hello-service` using the `hello-client`:

        ./gradlew :hello-client:bootRun --args="hello Bob"

    If successful, you will see the response message containing the `traceId` and `spanId` metadata printed to the console:

        e.client.hello.HelloClientApplication    : Sending message...
        e.client.hello.HelloClientApplication    : Response: Hello, Bob! [traceId: 'efb0cf0f-ee96-4e41-8777-db67e806323f', spanId: '146967266']
        
## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/springboot-rsocketmetadata-example/issues).

## License
MIT License

Copyright (c) 2019 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.