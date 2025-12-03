<template>
  <div class="chat-widget">
    <!-- Chat Icon -->
    <div class="chat-icon" @click="toggleChat">
      💬
    </div>

    <!-- Chat Box -->
    <transition name="fade">
      <div v-if="open" class="chatbox">
        <div class="header">
          Chat
          <button class="close-btn" @click="toggleChat">✖</button>
        </div>

        <div class="messages" ref="messagesContainer">
          <div v-for="(msg, index) in messages" :key="index" :class="msg.type">
            <span v-html="msg.content.replace(/\n/g, '<br>')"></span>
        </div>

        </div>

        <form @submit.prevent="sendMessage" class="input-area">
          <input
            type="text"
            v-model="userInput"
            placeholder="Type your message..."
            :disabled="loading"
          />
          <button type="submit" :disabled="loading || !userInput">
            {{ loading ? "..." : "Send" }}
          </button>
        </form>
      </div>
    </transition>
  </div>
</template>

<script setup lang="ts">
import { ref, nextTick } from 'vue'
import axios from 'axios'

// Define a type for messages
type Message = {
  type: 'user' | 'ai' | 'error'
  content: string
}

const open = ref(false)
const messages = ref<Message[]>([])
const userInput = ref('')
const loading = ref(false)
const messagesContainer = ref<HTMLDivElement | null>(null)

const toggleChat = () => {
  open.value = !open.value
  if (open.value) {
    nextTick(() => {
      scrollToBottom()
    })
  }
}

const scrollToBottom = () => {
  nextTick(() => {
    if (messagesContainer.value) {
      messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight
    }
  })
}

const sendMessage = async () => {
  if (!userInput.value.trim()) return

  messages.value.push({ type: 'user', content: userInput.value })
  scrollToBottom()

  loading.value = true

  try {
    const response = await axios.post<string>('/api/chat/message', {
      message: userInput.value
    })

    messages.value.push({ type: 'ai', content: response.data })
    scrollToBottom()
  } catch (error) {
    console.error(error)
    messages.value.push({ type: 'error', content: 'Error sending message' })
    scrollToBottom()
  } finally {
    loading.value = false
    userInput.value = ''
  }
}
</script>

<style scoped>
.chat-widget {
  position: fixed;
  bottom: 20px;
  right: 20px;
  font-family: sans-serif;
  z-index: 1000;
}

.chat-icon {
  width: 60px;
  height: 60px;
  background-color: #007bff;
  color: white;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 28px;
  cursor: pointer;
  box-shadow: 0 4px 10px rgba(0,0,0,0.2);
}
.chatbox {
  width: 350px;
  max-width: 90vw; /* never wider than viewport */
  height: 450px;
  display: flex;
  flex-direction: column;
  position: fixed; /* fixed is better for floating corner */
  bottom: 80px; /* space above icon */
  right: 20px;   /* space from right edge */
  border: 1px solid #ccc;
  border-radius: 12px;
  overflow: hidden;
  background-color: #fff;
  box-shadow: 0 4px 15px rgba(0,0,0,0.2);
  z-index: 1000;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #007bff;
  color: white;
  padding: 10px;
  font-weight: bold;
}

.close-btn {
  background: none;
  border: none;
  color: white;
  font-size: 18px;
  cursor: pointer;
}

.messages {
  flex: 1;
  padding: 10px;
  overflow-y: auto;
  background-color: #f9f9f9;
}

.messages div span {
  white-space: pre-wrap; /* preserves newlines */
}
.user {
  text-align: right;
  margin: 5px 0;
  color: white;
  background-color: #007bff;
  padding: 5px 10px;
  border-radius: 12px;
  display: inline-block;
}

.ai {
  text-align: left;
  margin: 5px 0;
  color: white;
  background-color: #6c757d;
  padding: 5px 10px;
  border-radius: 12px;
  display: inline-block;
}

.error {
  color: red;
  text-align: center;
  margin: 5px 0;
}

.input-area {
  display: flex;
  border-top: 1px solid #ccc;
}

input[type="text"] {
  flex: 1;
  padding: 10px;
  border: none;
  outline: none;
}

button {
  padding: 10px 15px;
  border: none;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

button:disabled {
  background-color: #aaa;
  cursor: not-allowed;
}

/* Fade transition */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>
