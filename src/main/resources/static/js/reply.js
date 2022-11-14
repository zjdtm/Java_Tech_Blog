async function getList({ bno, page, size, goLast }) {

    const result = await axios.get(`/replies/list/${bno}`, {params : {page, size}})

    return result.data

}

async function addReply(replyObj){

    const response = await axios.post(`/replies/`, replyObj)
    return response.data

}

async function getReply(rno) {
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

async function modifyReply(replyObj) {

    await axios.put(`/replies/${replyObj.rno}`, replyObj)
    return response.data;

}