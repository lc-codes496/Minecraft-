# Minecraft Java (Versão Custom)

Este é um clone simples do Minecraft em Java usando LWJGL.

## Importante
- O jogo **não vai funcionar corretamente** se você não colocar as texturas.
- Para o jogo rodar, crie a pasta `assets/textures/` e coloque as seguintes imagens:
  - `grass.png`
  - `dirt.png`
  - `stone.png`
  - `sand.png`
  - `water.png`

> Você pode usar qualquer imagem, mas os nomes devem ser exatamente iguais.

## Como executar
1. Compile o projeto usando o Java 17+.
2. Execute `Main.java`.
3. Use `W`, `A`, `S`, `D` para mover e `SPACE` para pular.
4. Movimente o mouse para olhar ao redor.

## Estrutura de pastas
MinecraftJava/
├─ src/
│   └─ src/
│       ├─ Main.java
│       ├─ Game.java
│       ├─ World.java
│       ├─ Player.java
│       ├─ Block.java
│       ├─ BlockType.java
│       ├─ Renderer.java
│       ├─ Input.java
│       └─ TextureLoader.java
├─ assets/
│   └─ textures/
│       ├─ grass.png
│       ├─ dirt.png
│       ├─ stone.png
│       ├─ sand.png
│       └─ water.png
├─ README.md
└─ .gitignore

## Aviso
- Este projeto é apenas um clone básico do Minecraft.
- Algumas funcionalidades ainda não estão implementadas (como mobs e crafting).
