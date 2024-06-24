import { jsPDF } from "jspdf";
import logo from '../assets/logo.png';

export function timeLeft(date) {
    let now = new Date();
    let endDate = new Date(date);

    if (isNaN(endDate)) {
        return "Invalid date provided.";
    }
    
    let timeDiff = endDate.getTime() - now.getTime();

    if (timeDiff < 0) {
        return "The date has already passed.";
    }

    let seconds = Math.floor(timeDiff / 1000);
    let minutes = Math.floor(seconds / 60);
    let hours = Math.floor(minutes / 60);
    let days = Math.floor(hours / 24);

    seconds = seconds % 60;
    minutes = minutes % 60;
    hours = hours % 24;

    return `${days} days ${hours} hours ${minutes} minutes ${seconds} seconds`;
}


export function extractUsernameFromEmail(email) {
    return email.split("@")[0];
}

export const handleDownload = (getAuction) => {
    const doc = new jsPDF();
    const img = new Image();
    img.src = logo;
    img.onload = () => {
        const pageWidth = doc.internal.pageSize.getWidth();
        const pageHeight = doc.internal.pageSize.getHeight();
        const margin = 10;
        const contentWidth = pageWidth - margin * 2;
        const contentStartX = margin;
        let currentY = margin;

        // Function to draw frame on each page
        const drawFrame = () => {
            doc.setDrawColor(0, 0, 255);
            doc.rect(margin / 2, margin / 2, pageWidth - margin, pageHeight - margin);
        };

        // Draw frame on first page
        drawFrame();

        const logoWidth = 50;
        const logoHeight = 40;
        const logoX = (pageWidth - logoWidth) / 2;

        doc.addImage(img, "PNG", logoX, 3, logoWidth, logoHeight);
        currentY += logoHeight + 1;

        doc.setFontSize(14);
        doc.text("Auction Information:", contentStartX, currentY);
        currentY += 10;
        doc.setFontSize(12);
        doc.text(`Auction Status: ${getAuction.auctionStatus}`, contentStartX, currentY);
        currentY += 10;
        doc.text(`Start Date: ${getAuction.auctionStartTime}`, contentStartX, currentY);
        currentY += 10;
        doc.text(`End Date: ${getAuction.auctionEndTime}`, contentStartX, currentY);

        currentY += 10;
        doc.setFontSize(14);
        doc.text("Item Information:", contentStartX, currentY);
        currentY += 10;
        doc.setFontSize(12);
        doc.text(`Title: ${getAuction.items[0].itemTitle}`, contentStartX, currentY);

        currentY += 10;
        const description = getAuction.items[0].itemDescription;
        const descriptionLines = doc.splitTextToSize(description, contentWidth);

        // Check if adding description exceeds current page height
        if (currentY + descriptionLines.length * 6 > pageHeight - margin) {
            doc.addPage(); // Add new page
            drawFrame(); // Draw frame on new page
            currentY = margin; // Reset currentY to top margin
        }
        doc.text(descriptionLines, contentStartX, currentY);

        currentY += descriptionLines.length * 6;

        doc.text(`Start Price: ${getAuction.items[0].startingPrice}`, contentStartX, currentY + 10);
        doc.text(`End Price: ${getAuction.items[0].currentPrice}`, contentStartX, currentY + 20);

        currentY += 40;

        doc.setFontSize(14);
        doc.text("Bids History:", contentStartX, currentY - 10);
        doc.setFontSize(12);

        // Splitting bids history into lines and handling page breaks
        const bidsHistoryLines = [];
        getAuction.bids.forEach((bid) => {
            const bidInfo = [`Bidder: ${bid.bidId}`, `Bid Amount: ${bid.bidAmount}`, `Bid Date: ${bid.bidTime}`];
            bidsHistoryLines.push(...bidInfo);
        });

        bidsHistoryLines.forEach((line) => {
            const lineHeight = doc.getTextDimensions(line).h;
            if (currentY + lineHeight > pageHeight - margin) {
                doc.addPage(); // Add new page
                drawFrame(); // Draw frame on new page
                currentY = margin; // Reset currentY to top margin
            }
            doc.text(line, contentStartX, currentY);
            currentY += lineHeight + 2; // Add some spacing between lines
        });

        doc.save("item-information.pdf");
    };
};
