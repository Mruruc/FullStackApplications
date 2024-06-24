
export const formatDate = (dateArray) => {
    if (!Array.isArray(dateArray)) {
      return "Invalid date";  
    }
    
    const correctedDateArray = [...dateArray];
    correctedDateArray[1] -= 1;  
    const date = new Date(...correctedDateArray);
    return date.toLocaleString("default", {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };
  