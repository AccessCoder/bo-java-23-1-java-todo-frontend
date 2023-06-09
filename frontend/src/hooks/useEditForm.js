import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

export default function useEditForm(todo, onSave) {
  const [formData, setFormData] = useState({
    description: todo.description,
    status: todo.status,
  })
  const navigate = useNavigate()

  const handleSubmit = event => {
    event.preventDefault()
    const updatedTodo = { ...todo, ...formData }
    onSave(updatedTodo).then(() => navigate('/'))
  }

  const resetForm = () => {
    setFormData({
      description: todo.description,
      status: todo.status,
    })
  }

  const handleChange = event => {
    const { name, value } = event.target
    setFormData({ ...formData, [name]: value })
  }

  return { formData, handleSubmit, handleChange, resetForm }
}
