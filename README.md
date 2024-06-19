# demo-langchain4j-llama2-local
Demo for LangChain4J with Local Model and Spring Boot

**Objective:**
Convert a CSV file containing email addresses into a single-line email list using semicolons as separators.

This example demonstrates two different approaches to achieve this:

## Implementation with Java NIO 
For this method, refer to the code here: [ManualTransformer.java](https://github.com/lofidewanto/demo-langchain4j-llama2-local/blob/main/src/main/java/com/example/demo/ManualTransformer.java)

## Implementation using LangChain4J with Ollama LLM Local Model
For this approach, see the implementation in: [LlamaTransformer.java](https://github.com/lofidewanto/demo-langchain4j-llama2-local/blob/main/src/main/java/com/example/demo/LlamaTransformer.java)

To use the LangChain4J method, you must first install and run the Ollama LLM local model. 
- You can download and install it from: [Download and Install Ollama](https://ollama.com/download)
- To run the local model llama3: `ollama run llama3`