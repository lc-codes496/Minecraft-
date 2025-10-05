#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <iostream>

// vertex shader e fragment shader simples inline (para teste)
const char* vertSrc = R"glsl(
#version 330 core
layout(location=0) in vec3 aPos;
uniform mat4 uMVP;
void main(){ gl_Position = uMVP * vec4(aPos,1.0); }
)glsl";
const char* fragSrc = R"glsl(
#version 330 core
out vec4 FragColor;
void main(){ FragColor = vec4(0.6, 0.8, 1.0, 1.0); }
)glsl";

static void framebuffer_size_callback(GLFWwindow* window, int w, int h){
    glViewport(0,0,w,h);
}

int main(){
    if(!glfwInit()){ std::cerr<<"GLFW init failed\n"; return -1; }
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR,3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR,3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* win = glfwCreateWindow(800,600,"MinecraftCPP", nullptr, nullptr);
    if(!win){ std::cerr<<"Window create failed\n"; glfwTerminate(); return -1; }
    glfwMakeContextCurrent(win);
    glfwSetFramebufferSizeCallback(win, framebuffer_size_callback);

    if(!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)){ std::cerr<<"GLAD failed\n"; return -1; }

    // cubo (8 vértices, 36 indices para faces)
    float vertices[] = {
        -0.5f,-0.5f,-0.5f,
         0.5f,-0.5f,-0.5f,
         0.5f, 0.5f,-0.5f,
        -0.5f, 0.5f,-0.5f,
        -0.5f,-0.5f, 0.5f,
         0.5f,-0.5f, 0.5f,
         0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f,
    };
    unsigned int indices[] = {
        0,1,2, 2,3,0,
        4,5,6, 6,7,4,
        0,1,5, 5,4,0,
        2,3,7, 7,6,2,
        0,3,7, 7,4,0,
        1,2,6, 6,5,1
    };

    unsigned int VBO, VAO, EBO;
    glGenVertexArrays(1,&VAO);
    glGenBuffers(1,&VBO);
    glGenBuffers(1,&EBO);

    glBindVertexArray(VAO);
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(indices), indices, GL_STATIC_DRAW);
    glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,3*sizeof(float),(void*)0);
    glEnableVertexAttribArray(0);

    // compilar shaders (simples)
    auto compile = [](const char* src, GLenum type)->unsigned int{
        unsigned int s = glCreateShader(type);
        glShaderSource(s,1,&src,nullptr);
        glCompileShader(s);
        int ok; glGetShaderiv(s, GL_COMPILE_STATUS, &ok);
        if(!ok){ char buf[512]; glGetShaderInfoLog(s,512,nullptr,buf); std::cerr<<buf<<"\n"; }
        return s;
    };
    unsigned int vs = compile(vertSrc, GL_VERTEX_SHADER);
    unsigned int fs = compile(fragSrc, GL_FRAGMENT_SHADER);
    unsigned int prog = glCreateProgram();
    glAttachShader(prog,vs); glAttachShader(prog,fs); glLinkProgram(prog);
    glDeleteShader(vs); glDeleteShader(fs);

    glEnable(GL_DEPTH_TEST);

    while(!glfwWindowShouldClose(win)){
        glClearColor(0.5f,0.7f,1.0f,1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glUseProgram(prog);
        // MVP identity por enquanto
        float mvp[16] = {
            1,0,0,0,
            0,1,0,0,
            0,0,1,0,
            0,0,0,1
        };
        int loc = glGetUniformLocation(prog,"uMVP");
        glUniformMatrix4fv(loc, 1, GL_FALSE, mvp);

        glBindVertexArray(VAO);
        glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_INT, 0);

        glfwSwapBuffers(win);
        glfwPollEvents();
    }

    glDeleteBuffers(1,&VBO);
    glDeleteBuffers(1,&EBO);
    glDeleteVertexArrays(1,&VAO);
    glDeleteProgram(prog);
    glfwDestroyWindow(win);
    glfwTerminate();
    return 0;
}
